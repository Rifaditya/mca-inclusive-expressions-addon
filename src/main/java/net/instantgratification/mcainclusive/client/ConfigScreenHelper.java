// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.client;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreenHelper {
    public static Screen createYaclScreen(Screen parent) {
        Option<Integer> scaleOption = Option.<Integer>createBuilder()
            .name(Component.literal("Chest Scale Multiplier (%)"))
            .description(OptionDescription.of(Component.literal("Sets the scaling multiplier for villager chest feature models (10% to 1000%). Default: 200%")))
            .binding(
                200,
                () -> (int) (MCAInclusiveExpressionsAddon.defaultMultiplier * 100),
                newVal -> MCAInclusiveExpressionsAddon.defaultMultiplier = newVal / 100.0
            )
            .controller(opt -> IntegerFieldControllerBuilder.create(opt).range(10, 1000))
            .build();

        Option<Boolean> genderOption = Option.<Boolean>createBuilder()
            .name(Component.literal("Allow All Genders"))
            .description(OptionDescription.of(Component.literal("Allows male and unassigned villagers to display chest feature customization options.")))
            .binding(
                false,
                () -> MCAInclusiveExpressionsAddon.allowAllGenders,
                newVal -> MCAInclusiveExpressionsAddon.allowAllGenders = newVal
            )
            .controller(TickBoxControllerBuilder::create)
            .build();

        OptionGroup mainGroup = OptionGroup.createBuilder()
            .name(Component.literal("Chest Feature Customization Settings"))
            .option(scaleOption)
            .option(genderOption)
            .build();

        YetAnotherConfigLib configLib = YetAnotherConfigLib.createBuilder()
            .title(Component.literal("MCA Inclusive Expressions Addon Config"))
            .category(ConfigCategory.createBuilder()
                .name(Component.literal("General"))
                .group(mainGroup)
                .build())
            .save(() -> MCAInclusiveExpressionsAddon.LOGGER.info("[MCA Inclusive Expressions Addon] Config saved."))
            .build();

        return configLib.generateScreen(parent);
    }
}
