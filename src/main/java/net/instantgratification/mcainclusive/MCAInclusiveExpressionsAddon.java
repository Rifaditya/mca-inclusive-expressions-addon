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
    public static GameRule<Boolean> ALLOW_ALL_GENDERS_RULE;
    public static GameRule<Integer> FULL_CHESTED_TRAIT_CHANCE_RULE;

    public static Traits.Trait FULL_CHESTED_TRAIT;

    public static double defaultLeftMultiplier = 1.0;
    public static double defaultRightMultiplier = 1.0;
    public static boolean linkSliders = true;
    public static boolean allowAllGenders = false;
    public static Object activeEditorScreen = null;

    @Override
    public void onInitialize() {
        ModVersionGuard.checkClass("MCA Inclusive Expressions Addon", "net.minecraft.world.level.gamerules.GameRule");

        try {
            MOD_CATEGORY = GameRuleCategory.register(Identifier.fromNamespaceAndPath(MOD_ID, "category"));

            ALLOW_ALL_GENDERS_RULE = Registry.register(
                BuiltInRegistries.GAME_RULE,
                "mca_inclusive_expressions:allow_all_genders",
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

        LOGGER.info("[MCA Inclusive Expressions Addon] Initialized v3.1.0+26.2.");
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

        try {
            var mc = net.minecraft.client.Minecraft.getInstance();
            if (mc != null && mc.getSingleplayerServer() != null && ALLOW_ALL_GENDERS_RULE != null) {
                return mc.getSingleplayerServer().getGameRules().get(ALLOW_ALL_GENDERS_RULE);
            }
        } catch (Throwable ignored) {
        }

        return false;
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
