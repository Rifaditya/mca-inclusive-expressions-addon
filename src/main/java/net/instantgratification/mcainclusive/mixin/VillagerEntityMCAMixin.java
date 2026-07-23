// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.entity.VillagerEntityMCA;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = VillagerEntityMCA.class, remap = false)
public abstract class VillagerEntityMCAMixin {
    @Shadow public abstract net.conczin.mca.entity.ai.Genetics getGenetics();

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void onReadAdditionalSaveData(ValueInput input, CallbackInfo ci) {
        if (input != null && this.getGenetics() instanceof GeneticsDuck duck) {
            try {
                CompoundTag tag = VillagerEntityMCA.readMcaSaveData(input);
                if (tag != null) {
                    readGeneticsFromTag(duck, tag);
                }
            } catch (Throwable ignored) {
            }
        }
    }

    @Inject(method = "readAdditionalSaveDataForEditor", at = @At("TAIL"))
    private void onReadAdditionalSaveDataForEditor(CompoundTag tag, CallbackInfo ci) {
        if (tag != null && this.getGenetics() instanceof GeneticsDuck duck) {
            readGeneticsFromTag(duck, tag);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void onAddAdditionalSaveData(ValueOutput output, CallbackInfo ci) {
        if (output != null && this.getGenetics() instanceof GeneticsDuck duck) {
            try {
                CompoundTag extraTag = new CompoundTag();
                writeGeneticsToTag(duck, extraTag);
                VillagerEntityMCA.storeMcaSaveData(output, extraTag);
            } catch (Throwable ignored) {
            }
        }
    }

    private static void readGeneticsFromTag(GeneticsDuck duck, CompoundTag rootTag) {
        if (rootTag == null) return;

        CompoundTag mcaData = rootTag.contains("MCAData") ? rootTag.getCompound("MCAData").orElse(rootTag) : rootTag;

        readKeysIntoDuck(duck, rootTag);
        if (mcaData != rootTag) {
            readKeysIntoDuck(duck, mcaData);
        }
    }

    private static void readKeysIntoDuck(GeneticsDuck duck, CompoundTag tag) {
        tag.getFloat("mca_inclusive_expressions:left_breast_size").ifPresent(duck::setLeftBreastSize);
        tag.getFloat("mca_inclusive_expressions:right_breast_size").ifPresent(duck::setRightBreastSize);

        tag.getFloat("mca_inclusive_expressions:left_breast_x").ifPresent(duck::setLeftBreastX);
        tag.getFloat("mca_inclusive_expressions:left_breast_y").ifPresent(duck::setLeftBreastY);
        tag.getFloat("mca_inclusive_expressions:left_breast_z").ifPresent(duck::setLeftBreastZ);

        tag.getFloat("mca_inclusive_expressions:right_breast_x").ifPresent(duck::setRightBreastX);
        tag.getFloat("mca_inclusive_expressions:right_breast_y").ifPresent(duck::setRightBreastY);
        tag.getFloat("mca_inclusive_expressions:right_breast_z").ifPresent(duck::setRightBreastZ);

        tag.getFloat("mca_inclusive_expressions:left_breast_pitch").ifPresent(duck::setLeftBreastPitch);
        tag.getFloat("mca_inclusive_expressions:left_breast_yaw").ifPresent(duck::setLeftBreastYaw);
        tag.getFloat("mca_inclusive_expressions:left_breast_roll").ifPresent(duck::setLeftBreastRoll);

        tag.getFloat("mca_inclusive_expressions:right_breast_pitch").ifPresent(duck::setRightBreastPitch);
        tag.getFloat("mca_inclusive_expressions:right_breast_yaw").ifPresent(duck::setRightBreastYaw);
        tag.getFloat("mca_inclusive_expressions:right_breast_roll").ifPresent(duck::setRightBreastRoll);
    }

    private static void writeGeneticsToTag(GeneticsDuck duck, CompoundTag tag) {
        tag.putFloat("mca_inclusive_expressions:left_breast_size", duck.getLeftBreastSize());
        tag.putFloat("mca_inclusive_expressions:right_breast_size", duck.getRightBreastSize());

        tag.putFloat("mca_inclusive_expressions:left_breast_x", duck.getLeftBreastX());
        tag.putFloat("mca_inclusive_expressions:left_breast_y", duck.getLeftBreastY());
        tag.putFloat("mca_inclusive_expressions:left_breast_z", duck.getLeftBreastZ());

        tag.putFloat("mca_inclusive_expressions:right_breast_x", duck.getRightBreastX());
        tag.putFloat("mca_inclusive_expressions:right_breast_y", duck.getRightBreastY());
        tag.putFloat("mca_inclusive_expressions:right_breast_z", duck.getRightBreastZ());

        tag.putFloat("mca_inclusive_expressions:left_breast_pitch", duck.getLeftBreastPitch());
        tag.putFloat("mca_inclusive_expressions:left_breast_yaw", duck.getLeftBreastYaw());
        tag.putFloat("mca_inclusive_expressions:left_breast_roll", duck.getLeftBreastRoll());

        tag.putFloat("mca_inclusive_expressions:right_breast_pitch", duck.getRightBreastPitch());
        tag.putFloat("mca_inclusive_expressions:right_breast_yaw", duck.getRightBreastYaw());
        tag.putFloat("mca_inclusive_expressions:right_breast_roll", duck.getRightBreastRoll());
    }
}
