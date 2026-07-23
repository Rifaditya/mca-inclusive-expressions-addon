// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.ai.Genetics;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Genetics.class, remap = false)
public abstract class GeneticsMixin implements GeneticsDuck {

    @Unique
    private float leftBreastSize = 1.0f;

    @Unique
    private float rightBreastSize = 1.0f;

    @Override
    public float getLeftBreastSize() {
        return this.leftBreastSize;
    }

    @Override
    public void setLeftBreastSize(float val) {
        this.leftBreastSize = val;
    }

    @Override
    public float getRightBreastSize() {
        return this.rightBreastSize;
    }

    @Override
    public void setRightBreastSize(float val) {
        this.rightBreastSize = val;
    }

    @Inject(method = "save", at = @At("TAIL"), remap = false)
    private void onSave(CompoundTag nbt, CallbackInfo ci) {
        nbt.putFloat("mca_inclusive_expressions:breast_left", this.leftBreastSize);
        nbt.putFloat("mca_inclusive_expressions:breast_right", this.rightBreastSize);
    }

    @Inject(method = "load", at = @At("TAIL"), remap = false)
    private void onLoad(CompoundTag nbt, CallbackInfo ci) {
        nbt.getFloat("mca_inclusive_expressions:breast_left").ifPresent(val -> this.leftBreastSize = val);
        nbt.getFloat("mca_inclusive_expressions:breast_right").ifPresent(val -> this.rightBreastSize = val);
    }
}
