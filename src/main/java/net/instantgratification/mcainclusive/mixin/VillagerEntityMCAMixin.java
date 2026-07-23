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
        CompoundTag mcaTag = rootTag.getCompound("MCAData").orElse(null);

        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_size").ifPresent(duck::setLeftBreastSize);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_size").ifPresent(duck::setRightBreastSize);

        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_x").ifPresent(duck::setLeftBreastX);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_y").ifPresent(duck::setLeftBreastY);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_z").ifPresent(duck::setLeftBreastZ);

        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_x").ifPresent(duck::setRightBreastX);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_y").ifPresent(duck::setRightBreastY);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_z").ifPresent(duck::setRightBreastZ);

        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_pitch").ifPresent(duck::setLeftBreastPitch);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_yaw").ifPresent(duck::setLeftBreastYaw);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:left_breast_roll").ifPresent(duck::setLeftBreastRoll);

        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_pitch").ifPresent(duck::setRightBreastPitch);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_yaw").ifPresent(duck::setRightBreastYaw);
        readKeyFromTags(rootTag, mcaTag, "mca_inclusive_expressions:right_breast_roll").ifPresent(duck::setRightBreastRoll);
    }

    private static java.util.Optional<Float> readKeyFromTags(CompoundTag rootTag, CompoundTag mcaTag, String key) {
        var opt = rootTag.getFloat(key);
        if (opt.isPresent()) {
            return opt;
        }
        if (mcaTag != null) {
            return mcaTag.getFloat(key);
        }
        return java.util.Optional.empty();
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
