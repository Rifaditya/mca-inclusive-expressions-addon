// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A VertexConsumer wrapper that clamps breast vertices against the torso surface plane.
 *
 * Uses dual-matrix relative transform: captures the PoseStack matrix at the torso reference frame
 * (Point A, after part.translateAndRotate) and uses its inverse to untransform already-transformed
 * vertices back into torso-local space. Vertices behind the torso wall (Z &lt; threshold) are clamped
 * to the wall, then re-transformed back to world/camera space.
 *
 * Camera matrices cancel out algebraically (A⁻¹ × B removes the shared camera prefix),
 * so this works identically in world rendering AND GUI editor screens.
 */
public class TorsoClippingVertexConsumer implements VertexConsumer {

    private final VertexConsumer delegate;
    private final Matrix4f torsoMatrix;
    private final Matrix4f torsoMatrixInverse;
    private final Vector3f scratch = new Vector3f();

    /** The Z threshold in torso-local space. Vertices behind this are clamped. */
    private static final float TORSO_WALL_Z = 0.0f;

    /**
     * @param delegate     The real VertexConsumer to forward clamped vertices to.
     * @param torsoMatrix  The PoseStack matrix captured BEFORE breast transforms (torso reference frame).
     *                     This matrix = Camera × Model × Torso. Its inverse untransforms vertices
     *                     from world/camera space back into torso-local space.
     */
    public TorsoClippingVertexConsumer(VertexConsumer delegate, Matrix4f torsoMatrix) {
        this.delegate = delegate;
        this.torsoMatrix = new Matrix4f(torsoMatrix);
        this.torsoMatrixInverse = new Matrix4f(torsoMatrix).invert();
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        // 1. Un-transform from world/camera space to torso-local space
        Vector3f torsoPos = torsoMatrixInverse.transformPosition(x, y, z, scratch);

        // 2. Clamp Z: if vertex is behind the torso wall (poking into body), push it to the wall
        //    +Z = into the torso (back-poke direction), -Z = outward (visible front)
        if (torsoPos.z() > TORSO_WALL_Z) {
            torsoPos.set(torsoPos.x(), torsoPos.y(), TORSO_WALL_Z);
            // 3. Re-transform clamped position back to world/camera space
            torsoMatrix.transformPosition(torsoPos, scratch);
            return delegate.addVertex(scratch.x(), scratch.y(), scratch.z());
        }

        // No clamping needed — forward vertex as-is
        return delegate.addVertex(x, y, z);
    }

    // --- Delegate all other VertexConsumer methods unchanged ---

    @Override
    public VertexConsumer setColor(int r, int g, int b, int a) {
        return delegate.setColor(r, g, b, a);
    }

    @Override
    public VertexConsumer setColor(int color) {
        return delegate.setColor(color);
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        return delegate.setUv(u, v);
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        return delegate.setUv1(u, v);
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        return delegate.setUv2(u, v);
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        return delegate.setNormal(x, y, z);
    }

    @Override
    public VertexConsumer setLineWidth(float width) {
        return delegate.setLineWidth(width);
    }
}
