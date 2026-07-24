// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.conczin.mca.entity.ai.Traits;
import net.fabricmc.api.ModInitializer;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.instantgratification.mcainclusive.mixin.VillagerEditorScreenAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MCAInclusiveExpressionsAddon implements ModInitializer {
    public static final String MOD_ID = "mca_inclusive_expressions_addon";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static GameRuleCategory MOD_CATEGORY;
    public static GameRule<Boolean> FORCE_ALL_BREASTED_RULE;
    public static GameRule<Integer> FULL_CHESTED_TRAIT_CHANCE_RULE;

    public static Traits.Trait FULL_CHESTED_TRAIT;

    public static double defaultLeftMultiplier = 1.0;
    public static double defaultRightMultiplier = 1.0;
    public static boolean linkSliders = true;
    public static boolean mirrorPosition = true;
    public static boolean mirrorRotation = true;
    public static boolean linkYPosWithSize = true;
    public static boolean allowAllGenders = false;
    public static Object activeEditorScreen = null;

    @Override
    public void onInitialize() {
        ModVersionGuard.checkClass("MCA Inclusive Expressions Addon", "net.minecraft.world.level.gamerules.GameRule");

        try {
            MOD_CATEGORY = GameRuleCategory.register(Identifier.fromNamespaceAndPath(MOD_ID, "category"));

            FORCE_ALL_BREASTED_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:force_all_breasted",
                new GameRule<>(
                    MOD_CATEGORY,
                    GameRuleType.BOOL,
                    BoolArgumentType.bool(),
                    GameRuleTypeVisitor::visitBoolean,
                    Codec.BOOL,
                    b -> b ? 1 : 0,
                    false,
                    FeatureFlagSet.of()
                )
            );

            FULL_CHESTED_TRAIT_CHANCE_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:full_chested_trait_chance",
                new GameRule<>(
                    MOD_CATEGORY,
                    GameRuleType.INT,
                    IntegerArgumentType.integer(0, 100),
                    GameRuleTypeVisitor::visitInteger,
                    Codec.INT,
                    i -> i,
                    5,
                    FeatureFlagSet.of()
                )
            );

            FULL_CHESTED_TRAIT = Traits.registerTrait("full_chested", 0.05f, 0.5f, true);
        } catch (Throwable t) {
            LOGGER.warn("Could not register GameRules or Traits for MCA Inclusive Expressions Addon", t);
        }

        LOGGER.info("[MCA Inclusive Expressions Addon] Initialized v4.4.17+26.2.");
    }

    /**
     * Calculates natural anatomical Y-position sag (-0.15f max at 4.44x scale) based on breast size scale.
     */
    public static float calculateAutoYSag(float breastSizeScale) {
        if (breastSizeScale <= 1.0f) {
            return 0.0f;
        }
        float norm = (breastSizeScale - 1.0f) / (4.44f - 1.0f);
        return -0.15f * Math.min(1.0f, Math.max(0.0f, norm));
    }

    /**
     * Samples a breast size scale (0.0x to 4.44x) following a smooth Gaussian (Normal) Bell-Curve:
     * Mean = 0.225 (22.5% center -> 1.0f scale), StdDev = 0.075 (7.5% -> 0.333f scale), maxing out at 4.44f scale.
     */
    public static float sampleGraphBreastSize(net.minecraft.util.RandomSource random) {
        double gaussian = (random != null ? random.nextGaussian() : (new java.util.Random()).nextGaussian());
        double pct = 0.225 + gaussian * 0.075;
        float clampedPct = (float) Math.min(1.0, Math.max(0.0, pct));
        return clampedPct * 4.44f;
    }

    /**
     * Samples a pair of breast sizes [leftScale, rightScale] with natural anatomical asymmetry (±5% variance between left & right).
     */
    public static float[] sampleAsymmetricBreastSizes(net.minecraft.util.RandomSource random) {
        float baseScale = sampleGraphBreastSize(random);
        if (baseScale <= 0.0f) {
            return new float[]{0.0f, 0.0f};
        }
        // +-5% of 4.44f max scale = +-0.222f
        float offset = (random != null ? (random.nextFloat() * 0.444f - 0.222f) : ((float) Math.random() * 0.444f - 0.222f));
        float left = (float) Math.min(4.44, Math.max(0.0, baseScale + offset));
        float right = (float) Math.min(4.44, Math.max(0.0, baseScale - offset));
        return new float[]{left, right};
    }

    public static GeneticsDuck getActiveGuiGenetics() {
        if (activeEditorScreen instanceof VillagerEditorScreenAccess access) {
            if (access.getVillagerVisualization() != null && access.getVillagerVisualization().getGenetics() instanceof GeneticsDuck duck) {
                return duck;
            }
            if (access.getVillager() != null && access.getVillager().getGenetics() instanceof GeneticsDuck duck) {
                return duck;
            }
        }
        return null;
    }

    public static int getMaxScaleLimit() {
        return 100;
    }

    public static float getLeftScaleMultiplier() {
        return (float) defaultLeftMultiplier;
    }

    public static float getRightScaleMultiplier() {
        return (float) defaultRightMultiplier;
    }

    public static float getAverageScaleMultiplier() {
        return (getLeftScaleMultiplier() + getRightScaleMultiplier()) / 2.0f;
    }

    public static float getScaleMultiplier() {
        return getAverageScaleMultiplier();
    }

    public static boolean isForceAllBreasted() {
        if (allowAllGenders) {
            return true;
        }

        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && FORCE_ALL_BREASTED_RULE != null) {
                return serverOpt.get().getGameRules().get(FORCE_ALL_BREASTED_RULE);
            }
        } catch (Throwable ignored) {
        }

        try {
            var mc = net.minecraft.client.Minecraft.getInstance();
            if (mc != null && mc.getSingleplayerServer() != null && FORCE_ALL_BREASTED_RULE != null) {
                return mc.getSingleplayerServer().getGameRules().get(FORCE_ALL_BREASTED_RULE);
            }
        } catch (Throwable ignored) {
        }

        return false;
    }

    public static boolean isAllowAllGenders() {
        return isForceAllBreasted();
    }

    public static int getFullChestedTraitChance() {
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && FULL_CHESTED_TRAIT_CHANCE_RULE != null) {
                return serverOpt.get().getGameRules().get(FULL_CHESTED_TRAIT_CHANCE_RULE);
            }
        } catch (Throwable ignored) {
        }

        try {
            var mc = net.minecraft.client.Minecraft.getInstance();
            if (mc != null && mc.getSingleplayerServer() != null && FULL_CHESTED_TRAIT_CHANCE_RULE != null) {
                return mc.getSingleplayerServer().getGameRules().get(FULL_CHESTED_TRAIT_CHANCE_RULE);
            }
        } catch (Throwable ignored) {
        }

        return 5;
    }
}
