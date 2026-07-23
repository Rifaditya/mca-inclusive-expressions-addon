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
import org.spongepowered.asm.mixin.Unique;
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

    @Unique private static String breastSubpage = "size";
    public static VillagerEditorScreen activeEditorScreen = null;

    protected VillagerEditorScreenMixin(Component title) {
        super(title);
    }

    public static GeneticsDuck getActiveGuiGenetics(Screen screen) {
        if (screen instanceof VillagerEditorScreen editor) {
            VillagerEditorScreenMixin mixin = (VillagerEditorScreenMixin) (Object) editor;
            if (mixin.villagerVisualization != null && mixin.villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                return duck;
            }
            if (mixin.villager != null && mixin.villager.getGenetics() instanceof GeneticsDuck duck) {
                return duck;
            }
        }
        return null;
    }

    @Unique
    private void syncPreviewGenetics() {
        if (villager != null && villagerVisualization != null) {
            if (villager.getGenetics() instanceof GeneticsDuck src && villagerVisualization.getGenetics() instanceof GeneticsDuck dst) {
                dst.setLeftBreastSize(src.getLeftBreastSize());
                dst.setRightBreastSize(src.getRightBreastSize());

                dst.setLeftBreastX(src.getLeftBreastX());
                dst.setLeftBreastY(src.getLeftBreastY());
                dst.setLeftBreastZ(src.getLeftBreastZ());

                dst.setRightBreastX(src.getRightBreastX());
                dst.setRightBreastY(src.getRightBreastY());
                dst.setRightBreastZ(src.getRightBreastZ());

                dst.setLeftBreastPitch(src.getLeftBreastPitch());
                dst.setLeftBreastYaw(src.getLeftBreastYaw());
                dst.setLeftBreastRoll(src.getLeftBreastRoll());

                dst.setRightBreastPitch(src.getRightBreastPitch());
                dst.setRightBreastYaw(src.getRightBreastYaw());
                dst.setRightBreastRoll(src.getRightBreastRoll());
            }
        }
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

    @Inject(method = "init", at = @At("TAIL"))
    private void onInitTail(CallbackInfo ci) {
        activeEditorScreen = (VillagerEditorScreen) (Object) this;
        syncPreviewGenetics();
    }

    @Inject(method = "onClose", at = @At("TAIL"), require = 0)
    private void onCloseTail(CallbackInfo ci) {
        if (activeEditorScreen == (Object) this) {
            activeEditorScreen = null;
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
        syncPreviewGenetics();

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
            int thirdWidth = DATA_WIDTH / 3;
            int fullWidth = DATA_WIDTH;
            int maxLimit = MCAInclusiveExpressionsAddon.getMaxScaleLimit();

            // Render 3 Sub-Category Toggle Buttons: [ Size ] | [ Position ] | [ Rotation ]
            ButtonWidget sizeTabBtn = this.addRenderableWidget(new ButtonWidget(
                leftColX, y, thirdWidth, 20,
                Component.literal("Size"),
                b -> { breastSubpage = "size"; this.setPage("breast_addon"); }
            ));
            sizeTabBtn.active = !"size".equals(breastSubpage);

            ButtonWidget posTabBtn = this.addRenderableWidget(new ButtonWidget(
                leftColX + thirdWidth, y, thirdWidth, 20,
                Component.literal("Position"),
                b -> { breastSubpage = "pos"; this.setPage("breast_addon"); }
            ));
            posTabBtn.active = !"pos".equals(breastSubpage);

            ButtonWidget rotTabBtn = this.addRenderableWidget(new ButtonWidget(
                leftColX + thirdWidth * 2, y, fullWidth - thirdWidth * 2, 20,
                Component.literal("Rotation"),
                b -> { breastSubpage = "rot"; this.setPage("breast_addon"); }
            ));
            rotTabBtn.active = !"rot".equals(breastSubpage);
            y += 24;

            if ("size".equals(breastSubpage)) {
                // --- Sub-Category 1: Size ---
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
                    () -> Component.literal("Adjusts left chest volume")
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
                    () -> Component.literal("Adjusts right chest volume")
                ));
                y += 24;

                boolean linked = MCAInclusiveExpressionsAddon.linkSliders;
                this.addRenderableWidget(new ButtonWidget(
                    leftColX, y, fullWidth, 20,
                    Component.literal("Slider Link Mode: " + (linked ? "LINKED (Symmetric)" : "UNLINKED (Asymmetric)")),
                    b -> {
                        MCAInclusiveExpressionsAddon.linkSliders = !MCAInclusiveExpressionsAddon.linkSliders;
                        if (MCAInclusiveExpressionsAddon.linkSliders) {
                            float leftVal = (float) MCAInclusiveExpressionsAddon.defaultLeftMultiplier;
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) leftVal = duck.getLeftBreastSize();
                            MCAInclusiveExpressionsAddon.defaultRightMultiplier = leftVal;
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                            if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                        }
                        refreshPreviewDimensions();
                        this.setPage("breast_addon");
                    }
                ));
                y += 24;

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
            } else if ("pos".equals(breastSubpage)) {
                // --- Sub-Category 2: Position ---
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
                y += 24;

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
                y += 24;

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
            } else if ("rot".equals(breastSubpage)) {
                // --- Sub-Category 3: 3D Rotations ---
                int curLeftPitch = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftPitch = (int) duck.getLeftBreastPitch();
                this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftPitch, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastPitch(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastPitch(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Pitch (X): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tips left chest up or down")
                ));

                int curRightPitch = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightPitch = (int) duck.getRightBreastPitch();
                this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightPitch, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastPitch(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastPitch(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Pitch (X): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tips right chest up or down")
                ));
                y += 24;

                int curLeftYaw = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftYaw = (int) duck.getLeftBreastYaw();
                this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftYaw, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastYaw(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastYaw(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Yaw (Y): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Swivels left cleavage angle outward/inward")
                ));

                int curRightYaw = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightYaw = (int) duck.getRightBreastYaw();
                this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightYaw, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastYaw(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastYaw(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Yaw (Y): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Swivels right cleavage angle outward/inward")
                ));
                y += 24;

                int curLeftRoll = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftRoll = (int) duck.getLeftBreastRoll();
                this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftRoll, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastRoll(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setLeftBreastRoll(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Roll (Z): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tilts left chest sideways left or right")
                ));

                int curRightRoll = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightRoll = (int) duck.getRightBreastRoll();
                this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightRoll, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastRoll(angle);
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastRoll(angle);
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Roll (Z): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tilts right chest sideways left or right")
                ));
            }
        }
    }
}
