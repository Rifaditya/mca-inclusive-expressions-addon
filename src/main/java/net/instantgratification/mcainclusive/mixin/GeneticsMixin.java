// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.ai.Genetics;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Genetics.class, remap = false)
public abstract class GeneticsMixin implements GeneticsDuck {
    @Shadow public abstract Gender getGender();
    @Shadow public abstract float getGene(Genetics.GeneType type);

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

    @Inject(method = "getBreastSize", at = @At("HEAD"), cancellable = true)
    private void onGetBreastSize(CallbackInfoReturnable<Float> cir) {
        if (MCAInclusiveExpressionsAddon.isAllowAllGenders()) {
            float geneVal = getGene(Genetics.BREAST);
            cir.setReturnValue(geneVal > 0 ? geneVal : 0.5f);
        }
    }
}
