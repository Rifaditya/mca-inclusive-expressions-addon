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

        LOGGER.info("[MCA Inclusive Expressions Addon] Initialized v4.4.11+26.2.");
    }

    /**
     * Samples a breast size scale (0.0x to 1.0x) following exact user probability tiers:
     * - 1 in 1000 (0.1% chance): 50% to 100% (0.50x to 1.00x)
     * - 1 in 10 (10.0% chance): 20% to 35% (0.20x to 0.35x)
     * - The Rest (89.9% chance): 0% to 20% (0.00x to 0.20x)
     */
    public static float sampleGraphBreastSize(net.minecraft.util.RandomSource random) {
        float roll = (random != null ? random.nextFloat() : (float) Math.random()) * 1000.0f;
        float inner = (random != null ? random.nextFloat() : (float) Math.random());

        if (roll < 1.0f) {
            // 1 in 1000 (0.1%): 50% to 100%
            return 0.50f + inner * 0.50f;
        } else if (roll < 101.0f) {
            // 1 in 10 (100 in 1000 = 10.0%): 20% to 35%
            return 0.20f + inner * 0.15f;
        } else {
            // The Rest (899 in 1000 = 89.9%): 0% to 20%
            return 0.00f + inner * 0.20f;
        }
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
