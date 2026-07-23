// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.util.compat.ButtonWidget;
import net.conczin.mca.client.gui.widget.IntegerSliderWidget;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = VillagerEditorScreen.class, remap = false)
public abstract class VillagerEditorScreenMixin extends Screen {
    @Shadow protected String page;
    @Shadow protected static int DATA_WIDTH;
    @Shadow protected VillagerEntityMCA villager;
    @Shadow protected VillagerEntityMCA villagerVisualization;

    @Shadow protected abstract void setPage(String page);
    @Shadow protected abstract void addCharacterSubpageTabs(int y, String selectedPage);
    @Shadow protected abstract void addCharacterSubpageTab(int x, int y, int width, String page, String selectedPage);

    protected VillagerEditorScreenMixin(Component title) {
        super(title);
    }

    private void refreshPreviewDimensions() {
        try {
            if (villager != null) {
                villager.refreshDimensions();
            }
            if (villagerVisualization != null) {
                villagerVisualization.refreshDimensions();
            }
        } catch (Throwable ignored) {
        }
    }

    @Inject(method = "addCharacterSubpageTabs", at = @At("HEAD"), cancellable = true)
    private void onAddCharacterSubpageTabs(int y, String selectedPage, CallbackInfo ci) {
        int tabWidth = DATA_WIDTH / 5;
        addCharacterSubpageTab(width / 2, y, tabWidth, "body", selectedPage);
        addCharacterSubpageTab(width / 2 + tabWidth, y, tabWidth, "clothing_style", selectedPage);
        addCharacterSubpageTab(width / 2 + tabWidth * 2, y, tabWidth, "hair_style", selectedPage);
        addCharacterSubpageTab(width / 2 + tabWidth * 3, y, tabWidth, "eyes", selectedPage);

        // Add 5th dedicated sub-tab: Breast
        ButtonWidget breastButton = this.addRenderableWidget(new ButtonWidget(
            width / 2 + tabWidth * 4,
            y,
            DATA_WIDTH - tabWidth * 4,
            20,
            Component.literal("Breast"),
            b -> this.setPage("breast_addon")
        ));
        breastButton.active = !"breast_addon".equals(selectedPage);

        ci.cancel();
    }

    @Inject(method = "setPage", at = @At("TAIL"))
    private void onSetPageTail(String page, CallbackInfo ci) {
        if ("breast_addon".equals(page)) {
            int y = this.height / 2 - 80;

            // Render sub-tabs header
            this.addCharacterSubpageTabs(y, "breast_addon");
            y += 24;

            int x = this.width / 2;
            int widgetWidth = DATA_WIDTH;

            // 1. Chest Scale Multiplier Slider
            int currentScalePct = (int) (MCAInclusiveExpressionsAddon.defaultMultiplier * 100);
            this.addRenderableWidget(new IntegerSliderWidget(
                x,
                y,
                widgetWidth,
                20,
                currentScalePct,
                10,
                1000,
                val -> {
                    MCAInclusiveExpressionsAddon.defaultMultiplier = val / 100.0;
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Chest Scale: " + val + "%"),
                () -> Component.literal("Adjusts chest model scaling multiplier")
            ));
            y += 24;

            // 2. Cleavage Angle Slider
            int currentAngle = MCAInclusiveExpressionsAddon.getCleavageAngle();
            this.addRenderableWidget(new IntegerSliderWidget(
                x,
                y,
                widgetWidth,
                20,
                currentAngle,
                0,
                30,
                val -> {
                    MCAInclusiveExpressionsAddon.defaultCleavageAngle = val;
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Cleavage Angle: " + val + "°"),
                () -> Component.literal("Adjusts outward cleavage separation angle for dual-mesh breasts")
            ));
            y += 24;

            // 3. Gender Representation Inclusivity Toggle
            boolean allowAll = MCAInclusiveExpressionsAddon.isAllowAllGenders();
            this.addRenderableWidget(new ButtonWidget(
                x,
                y,
                widgetWidth,
                20,
                Component.literal("Gender Inclusivity: " + (allowAll ? "ENABLED (All Genders)" : "DISABLED (Female Only)")),
                b -> {
                    MCAInclusiveExpressionsAddon.allowAllGenders = !MCAInclusiveExpressionsAddon.allowAllGenders;
                    refreshPreviewDimensions();
                    this.setPage("breast_addon");
                }
            ));
        }
    }
}
