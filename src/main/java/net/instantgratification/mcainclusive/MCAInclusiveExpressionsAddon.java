// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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

    public static GameRule<Integer> SCALE_RULE;
    public static GameRule<Integer> MAX_SCALE_LIMIT_RULE;
    public static GameRule<Boolean> ALLOW_ALL_GENDERS_RULE;

    public static double defaultLeftMultiplier = 1.0;
    public static double defaultRightMultiplier = 1.0;
    public static boolean linkSliders = true;
    public static int maxScaleLimit = 500;
    public static boolean allowAllGenders = false;

    @Override
    public void onInitialize() {
        ModVersionGuard.checkClass("MCA Inclusive Expressions Addon", "net.minecraft.world.level.gamerules.GameRule");

        try {
            SCALE_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:scale",
                new GameRule<>(
                    GameRuleCategory.MISC,
                    GameRuleType.INT,
                    IntegerArgumentType.integer(0, 2000),
                    GameRuleTypeVisitor::visitInteger,
                    Codec.intRange(0, 2000),
                    i -> i,
                    100,
                    FeatureFlagSet.of()
                )
            );

            MAX_SCALE_LIMIT_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:max_scale_limit",
                new GameRule<>(
                    GameRuleCategory.MISC,
                    GameRuleType.INT,
                    IntegerArgumentType.integer(100, 2000),
                    GameRuleTypeVisitor::visitInteger,
                    Codec.intRange(100, 2000),
                    i -> i,
                    500,
                    FeatureFlagSet.of()
                )
            );

            ALLOW_ALL_GENDERS_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:allow_all_genders",
                new GameRule<>(
                    GameRuleCategory.MISC,
                    GameRuleType.BOOL,
                    BoolArgumentType.bool(),
                    GameRuleTypeVisitor::visitBoolean,
                    Codec.BOOL,
                    b -> b ? 1 : 0,
                    false,
                    FeatureFlagSet.of()
                )
            );
        } catch (Throwable t) {
            LOGGER.warn("Could not register GameRules for MCA Inclusive Expressions Addon", t);
        }

        LOGGER.info("[MCA Inclusive Expressions Addon] Initialized v2.0.0+26.2 with direct 1:1 linear scaling and GameRule-governed max scale limits.");
    }

    public static int getMaxScaleLimit() {
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && MAX_SCALE_LIMIT_RULE != null) {
                return serverOpt.get().getGameRules().get(MAX_SCALE_LIMIT_RULE);
            }
        } catch (Throwable ignored) {
        }
        return maxScaleLimit;
    }

    public static float getLeftScaleMultiplier() {
        float multiplier = (float) defaultLeftMultiplier;
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && SCALE_RULE != null) {
                int ruleValue = serverOpt.get().getGameRules().get(SCALE_RULE);
                multiplier = (ruleValue / 100.0f) * (float) defaultLeftMultiplier;
            }
        } catch (Throwable ignored) {
        }
        return multiplier;
    }

    public static float getRightScaleMultiplier() {
        float multiplier = (float) defaultRightMultiplier;
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && SCALE_RULE != null) {
                int ruleValue = serverOpt.get().getGameRules().get(SCALE_RULE);
                multiplier = (ruleValue / 100.0f) * (float) defaultRightMultiplier;
            }
        } catch (Throwable ignored) {
        }
        return multiplier;
    }

    public static float getAverageScaleMultiplier() {
        return (getLeftScaleMultiplier() + getRightScaleMultiplier()) / 2.0f;
    }

    public static float getScaleMultiplier() {
        return getAverageScaleMultiplier();
    }

    public static boolean isAllowAllGenders() {
        if (allowAllGenders) {
            return true;
        }
        try {
            var serverOpt = net.conczin.mca.MCA.getServer();
            if (serverOpt.isPresent() && ALLOW_ALL_GENDERS_RULE != null) {
                return serverOpt.get().getGameRules().get(ALLOW_ALL_GENDERS_RULE);
            }
        } catch (Throwable ignored) {
        }
        return false;
    }
}
