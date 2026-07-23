// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.render.VillagerRenderState;
import net.instantgratification.mcainclusive.ducks.VillagerRenderStateDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = VillagerRenderState.class, remap = false)
public abstract class VillagerRenderStateMixin implements VillagerRenderStateDuck {

    @Unique private float leftBreastScale = 1.0f;
    @Unique private float rightBreastScale = 1.0f;

    @Unique private float leftBreastX = 0.0f;
    @Unique private float leftBreastY = 0.0f;
    @Unique private float leftBreastZ = 0.0f;

    @Unique private float rightBreastX = 0.0f;
    @Unique private float rightBreastY = 0.0f;
    @Unique private float rightBreastZ = 0.0f;

    @Unique private float leftBreastPitch = 0.0f;
    @Unique private float leftBreastYaw = 0.0f;
    @Unique private float leftBreastRoll = 0.0f;

    @Unique private float rightBreastPitch = 0.0f;
    @Unique private float rightBreastYaw = 0.0f;
    @Unique private float rightBreastRoll = 0.0f;

    @Override public float getLeftBreastScale() { return this.leftBreastScale; }
    @Override public void setLeftBreastScale(float val) { this.leftBreastScale = val; }

    @Override public float getRightBreastScale() { return this.rightBreastScale; }
    @Override public void setRightBreastScale(float val) { this.rightBreastScale = val; }

    @Override public float getLeftBreastX() { return this.leftBreastX; }
    @Override public void setLeftBreastX(float val) { this.leftBreastX = val; }

    @Override public float getLeftBreastY() { return this.leftBreastY; }
    @Override public void setLeftBreastY(float val) { this.leftBreastY = val; }

    @Override public float getLeftBreastZ() { return this.leftBreastZ; }
    @Override public void setLeftBreastZ(float val) { this.leftBreastZ = val; }

    @Override public float getRightBreastX() { return this.rightBreastX; }
    @Override public void setRightBreastX(float val) { this.rightBreastX = val; }

    @Override public float getRightBreastY() { return this.rightBreastY; }
    @Override public void setRightBreastY(float val) { this.rightBreastY = val; }

    @Override public float getRightBreastZ() { return this.rightBreastZ; }
    @Override public void setRightBreastZ(float val) { this.rightBreastZ = val; }

    @Override public float getLeftBreastPitch() { return this.leftBreastPitch; }
    @Override public void setLeftBreastPitch(float val) { this.leftBreastPitch = val; }

    @Override public float getLeftBreastYaw() { return this.leftBreastYaw; }
    @Override public void setLeftBreastYaw(float val) { this.leftBreastYaw = val; }

    @Override public float getLeftBreastRoll() { return this.leftBreastRoll; }
    @Override public void setLeftBreastRoll(float val) { this.leftBreastRoll = val; }

    @Override public float getRightBreastPitch() { return this.rightBreastPitch; }
    @Override public void setRightBreastPitch(float val) { this.rightBreastPitch = val; }

    @Override public float getRightBreastYaw() { return this.rightBreastYaw; }
    @Override public void setRightBreastYaw(float val) { this.rightBreastYaw = val; }

    @Override public float getRightBreastRoll() { return this.rightBreastRoll; }
    @Override public void setRightBreastRoll(float val) { this.rightBreastRoll = val; }
}
