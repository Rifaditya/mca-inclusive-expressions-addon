// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.client;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class ConfigScreenHelper {
    public static Screen createYaclScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
            .title(Component.literal("MCA Inclusive Expressions Settings"))
            .category(ConfigCategory.createBuilder()
                .name(Component.literal("General Settings"))
                .option(Option.<Integer>createBuilder()
                    .name(Component.literal("Chest Scale Multiplier (%)"))
                    .description(OptionDescription.of(Component.literal("Adjusts the maximum breast feature scale multiplier (100 = 1.0x, 200 = 2.0x).")))
                    .binding(
                        200,
                        () -> (int) (MCAInclusiveExpressionsAddon.getScaleMultiplier() * 100),
                        val -> MCAInclusiveExpressionsAddon.defaultMultiplier = val / 100.0
                    )
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(10, 1000).step(10))
                    .build())
                .option(Option.<Boolean>createBuilder()
                    .name(Component.literal("Gender-Inclusive Representation"))
                    .description(OptionDescription.of(Component.literal("Enables chest feature rendering and genetics for male and neutral character models.")))
                    .binding(
                        false,
                        MCAInclusiveExpressionsAddon::isAllowAllGenders,
                        val -> { }
                    )
                    .controller(BooleanControllerBuilder::create)
                    .build())
                .build())
            .build()
            .generateScreen(parent);
    }
}
