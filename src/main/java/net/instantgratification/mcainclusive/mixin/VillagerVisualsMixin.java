// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.entity.VillagerLike;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.instantgratification.mcainclusive.ducks.VillagerRenderStateDuck;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityRenderer.class)
public abstract class VillagerVisualsMixin {

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void onExtractRenderState(Entity entity, EntityRenderState state, float partialTick, CallbackInfo ci) {
        if (state instanceof VillagerRenderStateDuck stateDuck && entity != null) {
            GeneticsDuck geneticsDuck = null;

            if (entity instanceof VillagerEntityMCA villager && villager.getGenetics() instanceof GeneticsDuck duck) {
                geneticsDuck = duck;
            } else if (entity instanceof net.minecraft.world.entity.player.Player player) {
                try {
                    var opt = net.conczin.mca.MCAClient.getPlayerData(player.getUUID());
                    if (opt.isPresent() && opt.get().getGenetics() instanceof GeneticsDuck duck) {
                        geneticsDuck = duck;
                    } else {
                        VillagerLike<?> villagerLike = VillagerLike.toVillager(entity);
                        if (villagerLike != null && villagerLike.getGenetics() instanceof GeneticsDuck duck2) {
                            geneticsDuck = duck2;
                        }
                    }
                } catch (Throwable ignored) {
                }
            } else {
                try {
                    VillagerLike<?> villagerLike = VillagerLike.toVillager(entity);
                    if (villagerLike != null && villagerLike.getGenetics() instanceof GeneticsDuck duck) {
                        geneticsDuck = duck;
                    }
                } catch (Throwable ignored) {
                }
            }

            if (geneticsDuck != null) {
                boolean isMale = false;
                if (geneticsDuck instanceof net.conczin.mca.entity.ai.Genetics gen) {
                    isMale = (gen.getGender() == net.conczin.mca.entity.ai.relationship.Gender.MALE);
                }

                boolean hasFullChested = false;
                try {
                    if (entity instanceof VillagerEntityMCA villager && villager.getTraits() != null) {
                        hasFullChested = villager.getTraits().hasTrait(net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                    } else if (entity instanceof net.minecraft.world.entity.player.Player player) {
                        var opt = net.conczin.mca.MCAClient.getPlayerData(player.getUUID());
                        if (opt.isPresent() && opt.get().getTraits() != null) {
                            hasFullChested = opt.get().getTraits().hasTrait(net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                        }
                    }
                } catch (Throwable ignored) {
                }

                float leftScale = 0.0f;
                float rightScale = 0.0f;

                if (net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon.isForceAllBreasted() || !isMale || hasFullChested) {
                    leftScale = geneticsDuck.getLeftBreastSize();
                    rightScale = geneticsDuck.getRightBreastSize();

                    if (hasFullChested && leftScale <= 0.0f && rightScale <= 0.0f) {
                        leftScale = 1.0f;
                        rightScale = 1.0f;
                    }
                }

                stateDuck.setLeftBreastScale(leftScale);
                stateDuck.setRightBreastScale(rightScale);

                stateDuck.setLeftBreastX(geneticsDuck.getLeftBreastX());
                stateDuck.setLeftBreastY(geneticsDuck.getLeftBreastY());
                stateDuck.setLeftBreastZ(geneticsDuck.getLeftBreastZ());

                stateDuck.setRightBreastX(geneticsDuck.getRightBreastX());
                stateDuck.setRightBreastY(geneticsDuck.getRightBreastY());
                stateDuck.setRightBreastZ(geneticsDuck.getRightBreastZ());

                stateDuck.setLeftBreastPitch(geneticsDuck.getLeftBreastPitch());
                stateDuck.setLeftBreastYaw(geneticsDuck.getLeftBreastYaw());
                stateDuck.setLeftBreastRoll(geneticsDuck.getLeftBreastRoll());

                stateDuck.setRightBreastPitch(geneticsDuck.getRightBreastPitch());
                stateDuck.setRightBreastYaw(geneticsDuck.getRightBreastYaw());
                stateDuck.setRightBreastRoll(geneticsDuck.getRightBreastRoll());
            }
        }
    }
}
