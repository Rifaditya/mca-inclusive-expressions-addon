// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.render;

import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * Custom 3D Vertex Pipeline Guard enforcing a solid boundary plane line (minZBoundary).
 * Any vertex attempted to be drawn behind the boundary line (z < minZBoundary) is
 * automatically culled/clamped flush to minZBoundary, guaranteeing zero mesh penetration behind the torso.
 */
public class ClippingVertexConsumer implements VertexConsumer {
    private final VertexConsumer delegate;
    private final float minZBoundary;

    public ClippingVertexConsumer(VertexConsumer delegate, float minZBoundary) {
        this.delegate = delegate;
        this.minZBoundary = minZBoundary;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        if (z < minZBoundary) {
            z = minZBoundary;
        }
        return delegate.addVertex(x, y, z);
    }

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
