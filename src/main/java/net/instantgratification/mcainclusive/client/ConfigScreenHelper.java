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
        Option<Integer> leftScaleOption = Option.<Integer>createBuilder()
            .name(Component.literal("Left Breast Scale (%)"))
            .description(OptionDescription.of(Component.literal("Sets the left breast feature scaling multiplier (0% to 1000%). Default: 100%")))
            .binding(
                100,
                () -> (int) (MCAInclusiveExpressionsAddon.defaultLeftMultiplier * 100),
                newVal -> MCAInclusiveExpressionsAddon.defaultLeftMultiplier = newVal / 100.0
            )
            .controller(opt -> IntegerFieldControllerBuilder.create(opt).range(0, 1000))
            .build();

        Option<Integer> rightScaleOption = Option.<Integer>createBuilder()
            .name(Component.literal("Right Breast Scale (%)"))
            .description(OptionDescription.of(Component.literal("Sets the right breast feature scaling multiplier (0% to 1000%). Default: 100%")))
            .binding(
                100,
                () -> (int) (MCAInclusiveExpressionsAddon.defaultRightMultiplier * 100),
                newVal -> MCAInclusiveExpressionsAddon.defaultRightMultiplier = newVal / 100.0
            )
            .controller(opt -> IntegerFieldControllerBuilder.create(opt).range(0, 1000))
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
            .option(leftScaleOption)
            .option(rightScaleOption)
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
