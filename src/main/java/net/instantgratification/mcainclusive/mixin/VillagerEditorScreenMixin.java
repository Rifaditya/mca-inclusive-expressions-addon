// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
import net.conczin.mca.client.gui.widget.GeneSliderWidget;
import net.conczin.mca.client.gui.widget.IntegerSliderWidget;
import net.conczin.mca.client.gui.widget.TooltipButtonWidget;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.util.compat.ButtonWidget;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

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
        if ("body".equals(page)) {
            // Remove duplicate Breast slider from Body tab and expand Skin Mode button to full DATA_WIDTH
            List<AbstractWidget> toRemove = new ArrayList<>();
            for (var child : this.children()) {
                if (child instanceof GeneSliderWidget slider) {
                    if (slider.getX() == this.width / 2 && slider.getWidth() == DATA_WIDTH / 2) {
                        toRemove.add(slider);
                    }
                } else if (child instanceof TooltipButtonWidget button) {
                    if (button.getX() == this.width / 2 + DATA_WIDTH / 2 && button.getWidth() == DATA_WIDTH / 2) {
                        button.setX(this.width / 2);
                        button.setWidth(DATA_WIDTH);
                    }
                }
            }
            toRemove.forEach(this::removeWidget);
        } else if ("breast_addon".equals(page)) {
            int y = this.height / 2 - 85;

            // Render sub-tabs header
            this.addCharacterSubpageTabs(y, "breast_addon");
            y += 24;

            int leftColX = this.width / 2;
            int rightColX = this.width / 2 + DATA_WIDTH / 2;
            int halfWidth = DATA_WIDTH / 2;
            int fullWidth = DATA_WIDTH;
            int maxLimit = MCAInclusiveExpressionsAddon.getMaxScaleLimit();

            // --- Row 1: Left & Right Size Sliders ---
            int currentLeftPct = (int) (MCAInclusiveExpressionsAddon.defaultLeftMultiplier * 100);
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                currentLeftPct = (int) (duck.getLeftBreastSize() * 100.0f);
            }
            this.addRenderableWidget(new IntegerSliderWidget(
                leftColX, y, halfWidth, 20, currentLeftPct, 0, maxLimit,
                val -> {
                    float scale = val / 100.0f;
                    MCAInclusiveExpressionsAddon.defaultLeftMultiplier = scale;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                        duck.setLeftBreastSize(scale);
                        if (MCAInclusiveExpressionsAddon.linkSliders) duck.setRightBreastSize(scale);
                    }
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                        duck.setLeftBreastSize(scale);
                        if (MCAInclusiveExpressionsAddon.linkSliders) duck.setRightBreastSize(scale);
                    }
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Left Size: " + val + "%"),
                () -> Component.literal("Adjusts left chest size")
            ));

            int currentRightPct = (int) (MCAInclusiveExpressionsAddon.defaultRightMultiplier * 100);
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                currentRightPct = (int) (duck.getRightBreastSize() * 100.0f);
            }
            this.addRenderableWidget(new IntegerSliderWidget(
                rightColX, y, halfWidth, 20, currentRightPct, 0, maxLimit,
                val -> {
                    float scale = val / 100.0f;
                    MCAInclusiveExpressionsAddon.defaultRightMultiplier = scale;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                        duck.setRightBreastSize(scale);
                        if (MCAInclusiveExpressionsAddon.linkSliders) duck.setLeftBreastSize(scale);
                    }
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                        duck.setRightBreastSize(scale);
                        if (MCAInclusiveExpressionsAddon.linkSliders) duck.setLeftBreastSize(scale);
                    }
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Right Size: " + val + "%"),
                () -> Component.literal("Adjusts right chest size")
            ));
            y += 22;

            // --- Row 2: Left & Right X-Position Sliders (-1.0F to +1.0F mapped to -100 to +100) ---
            int curLeftX = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftX = (int) (duck.getLeftBreastX() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                leftColX, y, halfWidth, 20, curLeftX, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastX(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastX(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Left X-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts left chest horizontal position")
            ));

            int curRightX = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightX = (int) (duck.getRightBreastX() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                rightColX, y, halfWidth, 20, curRightX, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastX(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastX(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Right X-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts right chest horizontal position")
            ));
            y += 22;

            // --- Row 3: Left & Right Y-Position Sliders ---
            int curLeftY = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftY = (int) (duck.getLeftBreastY() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                leftColX, y, halfWidth, 20, curLeftY, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastY(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastY(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Left Y-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts left chest vertical position")
            ));

            int curRightY = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightY = (int) (duck.getRightBreastY() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                rightColX, y, halfWidth, 20, curRightY, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastY(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastY(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Right Y-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts right chest vertical position")
            ));
            y += 22;

            // --- Row 4: Left & Right Z-Position Sliders ---
            int curLeftZ = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftZ = (int) (duck.getLeftBreastZ() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                leftColX, y, halfWidth, 20, curLeftZ, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastZ(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastZ(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Left Z-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts left chest depth position")
            ));

            int curRightZ = 0;
            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightZ = (int) (duck.getRightBreastZ() * 100.0f);
            this.addRenderableWidget(new IntegerSliderWidget(
                rightColX, y, halfWidth, 20, curRightZ, -100, 100,
                val -> {
                    float offset = val / 100.0f;
                    if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastZ(offset);
                    if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastZ(offset);
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Right Z-Pos: " + (val >= 0 ? "+" : "") + val),
                () -> Component.literal("Adjusts right chest depth position")
            ));
            y += 22;

            // --- Row 5: Link Mode Button (Full DATA_WIDTH) ---
            boolean linked = MCAInclusiveExpressionsAddon.linkSliders;
            this.addRenderableWidget(new ButtonWidget(
                leftColX, y, fullWidth, 20,
                Component.literal("Slider Link Mode: " + (linked ? "LINKED (Symmetric)" : "UNLINKED (Asymmetric)")),
                b -> {
                    MCAInclusiveExpressionsAddon.linkSliders = !MCAInclusiveExpressionsAddon.linkSliders;
                    if (MCAInclusiveExpressionsAddon.linkSliders) {
                        float leftVal = (float) MCAInclusiveExpressionsAddon.defaultLeftMultiplier;
                        MCAInclusiveExpressionsAddon.defaultRightMultiplier = leftVal;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                    }
                    refreshPreviewDimensions();
                    this.setPage("breast_addon");
                }
            ));
            y += 22;

            // --- Row 6: Gender Representation Inclusivity Toggle ---
            boolean allowAll = MCAInclusiveExpressionsAddon.isAllowAllGenders();
            this.addRenderableWidget(new ButtonWidget(
                leftColX, y, fullWidth, 20,
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
