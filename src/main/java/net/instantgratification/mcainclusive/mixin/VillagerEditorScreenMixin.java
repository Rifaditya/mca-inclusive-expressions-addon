// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
import net.conczin.mca.client.gui.widget.GeneSliderWidget;
import net.conczin.mca.client.gui.widget.IntegerSliderWidget;
import net.conczin.mca.client.gui.widget.TooltipButtonWidget;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.entity.ai.Genetics;
import net.conczin.mca.util.compat.ButtonWidget;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
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
            int y = this.height / 2 - 80;

            // Render sub-tabs header
            this.addCharacterSubpageTabs(y, "breast_addon");
            y += 24;

            int x = this.width / 2;
            int widgetWidth = DATA_WIDTH;
            int maxLimit = MCAInclusiveExpressionsAddon.maxScaleLimit;

            // 1. Left Breast Size Slider
            int currentLeftPct = (int) (MCAInclusiveExpressionsAddon.defaultLeftMultiplier * 100);
            this.addRenderableWidget(new IntegerSliderWidget(
                x,
                y,
                widgetWidth,
                20,
                currentLeftPct,
                0,
                maxLimit,
                val -> {
                    MCAInclusiveExpressionsAddon.defaultLeftMultiplier = val / 100.0;
                    if (MCAInclusiveExpressionsAddon.linkSliders) {
                        MCAInclusiveExpressionsAddon.defaultRightMultiplier = val / 100.0;
                    }
                    float geneVal = (float) (val / (double) maxLimit);
                    if (villager != null) {
                        villager.getGenetics().setGene(Genetics.BREAST, geneVal);
                    }
                    if (villagerVisualization != null) {
                        villagerVisualization.getGenetics().setGene(Genetics.BREAST, geneVal);
                    }
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Left Breast Size: " + val + "%"),
                () -> Component.literal("Adjusts left chest feature size")
            ));
            y += 22;

            // 2. Right Breast Size Slider
            int currentRightPct = (int) (MCAInclusiveExpressionsAddon.defaultRightMultiplier * 100);
            this.addRenderableWidget(new IntegerSliderWidget(
                x,
                y,
                widgetWidth,
                20,
                currentRightPct,
                0,
                maxLimit,
                val -> {
                    MCAInclusiveExpressionsAddon.defaultRightMultiplier = val / 100.0;
                    if (MCAInclusiveExpressionsAddon.linkSliders) {
                        MCAInclusiveExpressionsAddon.defaultLeftMultiplier = val / 100.0;
                    }
                    float geneVal = (float) (val / (double) maxLimit);
                    if (villager != null) {
                        villager.getGenetics().setGene(Genetics.BREAST, geneVal);
                    }
                    if (villagerVisualization != null) {
                        villagerVisualization.getGenetics().setGene(Genetics.BREAST, geneVal);
                    }
                    refreshPreviewDimensions();
                },
                val -> Component.literal("Right Breast Size: " + val + "%"),
                () -> Component.literal("Adjusts right chest feature size")
            ));
            y += 22;

            // 3. Slider Link Mode Toggle
            boolean linked = MCAInclusiveExpressionsAddon.linkSliders;
            this.addRenderableWidget(new ButtonWidget(
                x,
                y,
                widgetWidth,
                20,
                Component.literal("Slider Link Mode: " + (linked ? "LINKED (Symmetric)" : "UNLINKED (Asymmetric)")),
                b -> {
                    MCAInclusiveExpressionsAddon.linkSliders = !MCAInclusiveExpressionsAddon.linkSliders;
                    if (MCAInclusiveExpressionsAddon.linkSliders) {
                        MCAInclusiveExpressionsAddon.defaultRightMultiplier = MCAInclusiveExpressionsAddon.defaultLeftMultiplier;
                    }
                    refreshPreviewDimensions();
                    this.setPage("breast_addon");
                }
            ));
            y += 22;

            // 4. Max Scale Limit Toggle
            this.addRenderableWidget(new ButtonWidget(
                x,
                y,
                widgetWidth,
                20,
                Component.literal("Max Scale Limit: " + maxLimit + "%" + (maxLimit == 200 ? " (Default)" : "")),
                b -> {
                    if (maxLimit == 200) {
                        MCAInclusiveExpressionsAddon.maxScaleLimit = 300;
                    } else if (maxLimit == 300) {
                        MCAInclusiveExpressionsAddon.maxScaleLimit = 500;
                    } else if (maxLimit == 500) {
                        MCAInclusiveExpressionsAddon.maxScaleLimit = 1000;
                    } else {
                        MCAInclusiveExpressionsAddon.maxScaleLimit = 200;
                    }
                    this.setPage("breast_addon");
                }
            ));
            y += 22;

            // 5. Gender Representation Inclusivity Toggle
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
