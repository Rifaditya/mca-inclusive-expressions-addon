// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.VillagerLike;
import net.conczin.mca.entity.ai.Genetics;
import net.conczin.mca.entity.ai.Traits;
import net.conczin.mca.entity.ai.relationship.Gender;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Traits.class, remap = false)
public abstract class TraitsMixin {

    @Shadow private VillagerLike<?> entity;

    @Shadow public abstract void addTrait(Traits.Trait trait);
    @Shadow public abstract void removeTrait(Traits.Trait trait);

    @Inject(method = "randomize", at = @At("TAIL"))
    private void onRandomizeTraits(CallbackInfo ci) {
        if (this.entity != null && MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT != null) {
            Genetics genetics = this.entity.getGenetics();
            if (genetics != null && genetics.getGender() == Gender.MALE) {
                int chancePct = MCAInclusiveExpressionsAddon.getFullChestedTraitChance();
                boolean shouldHave = false;

                if (chancePct >= 100) {
                    shouldHave = true;
                } else if (chancePct > 0) {
                    var mob = this.entity.asEntity();
                    if (mob != null && mob.getRandom() != null) {
                        shouldHave = (mob.getRandom().nextInt(100) < chancePct);
                    } else {
                        shouldHave = (java.util.concurrent.ThreadLocalRandom.current().nextInt(100) < chancePct);
                    }
                }

                if (shouldHave) {
                    this.addTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                    if (genetics instanceof GeneticsDuck duck) {
                        if (duck.getLeftBreastSize() <= 0.0f) duck.setLeftBreastSize(1.0f);
                        if (duck.getRightBreastSize() <= 0.0f) duck.setRightBreastSize(1.0f);
                    }
                } else {
                    this.removeTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                    if (genetics instanceof GeneticsDuck duck) {
                        duck.setLeftBreastSize(0.0f);
                        duck.setRightBreastSize(0.0f);
                    }
                }
            }
        }
    }
}
