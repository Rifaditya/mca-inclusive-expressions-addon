// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Vector4f;

/**
 * Custom 3D Torso-Space Polygon Culling Guard.
 * Transforms incoming local vertices into Orthogonal Torso Space. If a vertex extends
 * past the back wall of the torso (torsoZ < minZBoundary), it clamps torso Z flush to the
 * back wall plane (-2.0f / 16.0f) and inverse-transforms back to local space.
 *
 * Guarantees zero triangular tips poking out the back, zero flat board artifacts,
 * and dynamic support for square or angled breasts at any scale/rotation.
 */
public class ClippingVertexConsumer implements VertexConsumer {
    private final VertexConsumer delegate;
    private final Matrix4f torsoMatrix;
    private final Matrix4f invTorsoMatrix;
    private final float minZBoundary;

    public ClippingVertexConsumer(VertexConsumer delegate, Matrix4f torsoMatrix, float minZBoundary) {
        this.delegate = delegate;
        this.torsoMatrix = new Matrix4f(torsoMatrix);
        this.invTorsoMatrix = new Matrix4f(torsoMatrix).invert();
        this.minZBoundary = minZBoundary;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        Vector4f torsoPos = this.torsoMatrix.transform(new Vector4f(x, y, z, 1.0f));
        if (torsoPos.z() < this.minZBoundary) {
            torsoPos.set(torsoPos.x(), torsoPos.y(), this.minZBoundary, 1.0f);
            Vector4f localPos = this.invTorsoMatrix.transform(torsoPos);
            x = localPos.x();
            y = localPos.y();
            z = localPos.z();
        }
        return this.delegate.addVertex(x, y, z);
    }

    @Override
    public VertexConsumer setColor(int r, int g, int b, int a) {
        return this.delegate.setColor(r, g, b, a);
    }

    @Override
    public VertexConsumer setColor(int color) {
        return this.delegate.setColor(color);
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        return this.delegate.setUv(u, v);
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        return this.delegate.setUv1(u, v);
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        return this.delegate.setUv2(u, v);
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        return this.delegate.setNormal(x, y, z);
    }

    @Override
    public VertexConsumer setLineWidth(float width) {
        return this.delegate.setLineWidth(width);
    }
}
