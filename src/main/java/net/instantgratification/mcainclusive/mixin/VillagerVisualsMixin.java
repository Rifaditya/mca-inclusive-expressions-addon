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
                stateDuck.setLeftBreastScale(geneticsDuck.getLeftBreastSize());
                stateDuck.setRightBreastScale(geneticsDuck.getRightBreastSize());

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
