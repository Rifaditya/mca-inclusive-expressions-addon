// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
import net.conczin.mca.client.gui.widget.GeneSliderWidget;
import net.conczin.mca.client.gui.widget.IntegerSliderWidget;
import net.conczin.mca.client.gui.widget.TooltipButtonWidget;
import net.conczin.mca.entity.VillagerEntityMCA;
import net.conczin.mca.util.compat.ButtonWidget;
import net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon;
import net.instantgratification.mcainclusive.ducks.ExtendedSliderWidgetDuck;
import net.instantgratification.mcainclusive.ducks.GeneticsDuck;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = VillagerEditorScreen.class, remap = false)
public abstract class VillagerEditorScreenMixin extends Screen {
    @Shadow protected String page;
    @Shadow protected static int DATA_WIDTH;
    @Shadow protected VillagerEntityMCA villager;
    @Shadow protected VillagerEntityMCA villagerVisualization;
    @Shadow protected java.util.UUID villagerUUID;
    @Shadow protected java.util.UUID playerUUID;
    @Shadow protected int traitPage;

    @Shadow protected abstract void setPage(String page);
    @Shadow protected abstract void addCharacterSubpageTabs(int y, String selectedPage);
    @Shadow protected abstract void addCharacterSubpageTab(int x, int y, int width, String page, String selectedPage);

    @Unique private static String breastSubpage = "size";

    /**
     * @author MCA Inclusive Expressions Addon
     * @reason Include dynamic traits from Traits.TRAIT_REGISTRY
     */
    @Overwrite(remap = false)
    protected net.conczin.mca.entity.ai.Traits.Trait[] getValidTraits() {
        java.util.Collection<net.conczin.mca.entity.ai.Traits.Trait> allTraits = net.conczin.mca.entity.ai.Traits.TRAIT_REGISTRY.values();
        java.util.List<net.conczin.mca.entity.ai.Traits.Trait> valid = new java.util.ArrayList<>();
        for (net.conczin.mca.entity.ai.Traits.Trait t : allTraits) {
            boolean isPlayer = villagerUUID != null && villagerUUID.equals(playerUUID);
            if (isPlayer) {
                if ((net.conczin.mca.Config.getInstance().bypassTraitRestrictions || t.isUsableOnPlayer()) && t.isEnabled()) {
                    valid.add(t);
                }
            } else {
                if (t.isEnabled()) {
                    valid.add(t);
                }
            }
        }
        return valid.toArray(new net.conczin.mca.entity.ai.Traits.Trait[0]);
    }

    protected VillagerEditorScreenMixin(Component title) {
        super(title);
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

    @Unique
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

    @Inject(method = "createEditorData", at = @At("TAIL"), cancellable = true, remap = false)
    private void onCreateEditorData(CallbackInfoReturnable<CompoundTag> cir) {
        CompoundTag tag = cir.getReturnValue();
        if (tag == null) {
            tag = new CompoundTag();
        }

        GeneticsDuck src = null;
        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
            src = duck;
        } else if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
            src = duck;
        }

        if (src != null) {
            tag.putFloat("mca_inclusive_expressions:left_breast_size", src.getLeftBreastSize());
            tag.putFloat("mca_inclusive_expressions:right_breast_size", src.getRightBreastSize());

            tag.putFloat("mca_inclusive_expressions:left_breast_x", src.getLeftBreastX());
            tag.putFloat("mca_inclusive_expressions:left_breast_y", src.getLeftBreastY());
            tag.putFloat("mca_inclusive_expressions:left_breast_z", src.getLeftBreastZ());

            tag.putFloat("mca_inclusive_expressions:right_breast_x", src.getRightBreastX());
            tag.putFloat("mca_inclusive_expressions:right_breast_y", src.getRightBreastY());
            tag.putFloat("mca_inclusive_expressions:right_breast_z", src.getRightBreastZ());

            tag.putFloat("mca_inclusive_expressions:left_breast_pitch", src.getLeftBreastPitch());
            tag.putFloat("mca_inclusive_expressions:left_breast_yaw", src.getLeftBreastYaw());
            tag.putFloat("mca_inclusive_expressions:left_breast_roll", src.getLeftBreastRoll());

            tag.putFloat("mca_inclusive_expressions:right_breast_pitch", src.getRightBreastPitch());
            tag.putFloat("mca_inclusive_expressions:right_breast_yaw", src.getRightBreastYaw());
            tag.putFloat("mca_inclusive_expressions:right_breast_roll", src.getRightBreastRoll());
        }

        cir.setReturnValue(tag);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInitTail(CallbackInfo ci) {
        MCAInclusiveExpressionsAddon.activeEditorScreen = this;
        syncPreviewGenetics();
    }

    @Inject(method = "onClose", at = @At("TAIL"), require = 0)
    private void onCloseTail(CallbackInfo ci) {
        if (MCAInclusiveExpressionsAddon.activeEditorScreen == (Object) this) {
            MCAInclusiveExpressionsAddon.activeEditorScreen = null;
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
        } else if ("traits".equals(page)) {
            // 1. Defensive while-loop purge: repeatedly remove native trait widgets until ZERO remain (eliminates index-shifting bugs!)
            int startY = this.height / 2 - 85;

            boolean foundAny;
            do {
                foundAny = false;
                for (var child : new ArrayList<>(this.children())) {
                    if (child instanceof AbstractWidget widget) {
                        if (widget.getX() >= this.width / 2 - 5 && widget.getWidth() >= DATA_WIDTH - 5) {
                            this.removeWidget(widget);
                            foundAny = true;
                            break;
                        }
                    }
                }
            } while (foundAny);

            // Dynamically calculate traitStartY (2px below < Page X > header bar!)
            int traitStartY = startY + 46;
            for (var child : this.children()) {
                if (child instanceof AbstractWidget widget && widget.getX() >= this.width / 2 && widget.getY() < this.height / 2) {
                    if (widget instanceof ButtonWidget b && b.getMessage() != null && b.getMessage().getString().contains("Page")) {
                        traitStartY = b.getY() + b.getHeight() + 2;
                        break;
                    }
                }
            }

            // 2. Build complete valid traits list with FULL_CHESTED_TRAIT at Index 0!
            List<net.conczin.mca.entity.ai.Traits.Trait> traitList = new ArrayList<>();
            if (MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT != null) {
                traitList.add(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
            }

            boolean isPlayer = villagerUUID != null && villagerUUID.equals(playerUUID);
            for (net.conczin.mca.entity.ai.Traits.Trait t : net.conczin.mca.entity.ai.Traits.TRAIT_REGISTRY.values()) {
                if (t != null && t != MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT && t.isEnabled()) {
                    if (isPlayer) {
                        if (net.conczin.mca.Config.getInstance().bypassTraitRestrictions || t.isUsableOnPlayer()) {
                            traitList.add(t);
                        }
                    } else {
                        traitList.add(t);
                    }
                }
            }

            // 3. Validate traitPage bounds (6 traits per page)
            int totalTraits = traitList.size();
            int maxPages = (totalTraits + 5) / 6;
            if (maxPages < 1) maxPages = 1;
            if (this.traitPage >= maxPages) {
                this.traitPage = 0;
            }
            if (this.traitPage < 0) {
                this.traitPage = maxPages - 1;
            }

            // 4. Update Page Navigation label widget if present
            for (var child : this.children()) {
                if (child instanceof ButtonWidget b && b.getX() > this.width / 2 && b.getX() < this.width / 2 + DATA_WIDTH - 40 && b.getY() == startY + 24) {
                    b.setMessage(Component.literal("Page " + (this.traitPage + 1)));
                }
            }

            // 5. Render up to 6 traits cleanly for current traitPage (ZERO CUTOFF at bottom!)
            int startIndex = this.traitPage * 6;
            int leftColX = this.width / 2;

            for (int i = 0; i < 6; i++) {
                int idx = startIndex + i;
                if (idx < totalTraits) {
                    net.conczin.mca.entity.ai.Traits.Trait trait = traitList.get(idx);
                    boolean hasTrait = false;
                    if (villager != null && villager.getTraits() != null) {
                        hasTrait = villager.getTraits().hasTrait(trait);
                    }

                    Component labelText = trait.getName().copy()
                        .withStyle(hasTrait ? net.minecraft.ChatFormatting.GREEN : net.minecraft.ChatFormatting.GRAY);

                    this.addRenderableWidget(new ButtonWidget(
                        leftColX, traitStartY + (i * 22), DATA_WIDTH, 20,
                        labelText,
                        b -> {
                            if (villager != null && villager.getTraits() != null) {
                                if (villager.getTraits().hasTrait(trait)) {
                                    villager.getTraits().removeTrait(trait);
                                    b.setMessage(trait.getName().copy().withStyle(net.minecraft.ChatFormatting.GRAY));
                                    if (trait == MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT) {
                                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                                            duck.setLeftBreastSize(0.0f);
                                            duck.setRightBreastSize(0.0f);
                                        }
                                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                                            duck.setLeftBreastSize(0.0f);
                                            duck.setRightBreastSize(0.0f);
                                        }
                                    }
                                } else {
                                    villager.getTraits().addTrait(trait);
                                    b.setMessage(trait.getName().copy().withStyle(net.minecraft.ChatFormatting.GREEN));
                                    if (trait == MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT) {
                                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                                            if (duck.getLeftBreastSize() <= 0) duck.setLeftBreastSize(1.0f);
                                            if (duck.getRightBreastSize() <= 0) duck.setRightBreastSize(1.0f);
                                        }
                                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                                            if (duck.getLeftBreastSize() <= 0) duck.setLeftBreastSize(1.0f);
                                            if (duck.getRightBreastSize() <= 0) duck.setRightBreastSize(1.0f);
                                        }
                                    }
                                }
                                syncPreviewGenetics();
                                refreshPreviewDimensions();
                            }
                        }
                    ));
                }
            }
        } else if ("body".equals(page)) {
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
            int maxLimit = 100;

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
                // --- Sub-Category 1: Size (Direct Linear Mapping: 0% = 0.0, 100% = 4.44f) ---
                int currentLeftPct = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                    float val = duck.getLeftBreastSize();
                    currentLeftPct = val >= 0 ? (int) (val / 4.44f * 100.0f) : 0;
                }
                int currentRightPct = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                    float val = duck.getRightBreastSize();
                    currentRightPct = val >= 0 ? (int) (val / 4.44f * 100.0f) : 0;
                }

                final IntegerSliderWidget[] leftSizeHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightSizeHolder = new IntegerSliderWidget[1];

                leftSizeHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, currentLeftPct, 0, maxLimit,
                    val -> {
                        float scale = (val / 100.0f) * 4.44f; // Direct linear scale up to 4.44f
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastSize(scale);
                            if (MCAInclusiveExpressionsAddon.linkSliders) duck.setRightBreastSize(scale);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastSize(scale);
                            if (MCAInclusiveExpressionsAddon.linkSliders) duck.setRightBreastSize(scale);
                        }
                        if (MCAInclusiveExpressionsAddon.linkSliders && rightSizeHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, 0, maxLimit);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Size: " + val + "%"),
                    () -> Component.literal("Adjusts left chest volume (100% = 4.44x max scale)")
                ));

                rightSizeHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, currentRightPct, 0, maxLimit,
                    val -> {
                        float scale = (val / 100.0f) * 4.44f; // Direct linear scale up to 4.44f
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastSize(scale);
                            if (MCAInclusiveExpressionsAddon.linkSliders) duck.setLeftBreastSize(scale);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastSize(scale);
                            if (MCAInclusiveExpressionsAddon.linkSliders) duck.setLeftBreastSize(scale);
                        }
                        if (MCAInclusiveExpressionsAddon.linkSliders && leftSizeHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, 0, maxLimit);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Size: " + val + "%"),
                    () -> Component.literal("Adjusts right chest volume (100% = 4.44x max scale)")
                ));
                y += 24;

                boolean linked = MCAInclusiveExpressionsAddon.linkSliders;
                this.addRenderableWidget(new ButtonWidget(
                    leftColX, y, fullWidth, 20,
                    Component.literal("Slider Link Mode: " + (linked ? "LINKED (Symmetric)" : "UNLINKED (Asymmetric)")),
                    b -> {
                        MCAInclusiveExpressionsAddon.linkSliders = !MCAInclusiveExpressionsAddon.linkSliders;
                        if (MCAInclusiveExpressionsAddon.linkSliders) {
                            float leftVal = 1.0f;
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) leftVal = duck.getLeftBreastSize();
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                            if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) duck.setRightBreastSize(leftVal);
                        }
                        refreshPreviewDimensions();
                        this.setPage("breast_addon");
                    }
                ));
            } else if ("pos".equals(breastSubpage)) {
                // --- Sub-Category 2: Position ---
                int curLeftX = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftX = (int) (duck.getLeftBreastX() * 100.0f);
                int curRightX = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightX = (int) (duck.getRightBreastX() * 100.0f);

                final IntegerSliderWidget[] leftPosXHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightPosXHolder = new IntegerSliderWidget[1];

                leftPosXHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftX, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastX(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastX(-offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastX(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastX(-offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && rightPosXHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left X-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts left chest horizontal position")
                ));

                rightPosXHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightX, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastX(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastX(-offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastX(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastX(-offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && leftPosXHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right X-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts right chest horizontal position")
                ));
                y += 24;

                int curLeftY = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftY = (int) (duck.getLeftBreastY() * 100.0f);
                int curRightY = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightY = (int) (duck.getRightBreastY() * 100.0f);

                final IntegerSliderWidget[] leftPosYHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightPosYHolder = new IntegerSliderWidget[1];

                leftPosYHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftY, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastY(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastY(offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastY(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastY(offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && rightPosYHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Y-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts left chest vertical position")
                ));

                rightPosYHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightY, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastY(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastY(offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastY(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastY(offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && leftPosYHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Y-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts right chest vertical position")
                ));
                y += 24;

                int curLeftZ = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftZ = (int) (duck.getLeftBreastZ() * 100.0f);
                int curRightZ = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightZ = (int) (duck.getRightBreastZ() * 100.0f);

                final IntegerSliderWidget[] leftPosZHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightPosZHolder = new IntegerSliderWidget[1];

                leftPosZHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftZ, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastZ(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastZ(offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastZ(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setRightBreastZ(offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && rightPosZHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Z-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts left chest depth position")
                ));

                rightPosZHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightZ, -100, 100,
                    val -> {
                        float offset = val / 100.0f;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastZ(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastZ(offset);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastZ(offset);
                            if (MCAInclusiveExpressionsAddon.mirrorPosition) duck.setLeftBreastZ(offset);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorPosition && leftPosZHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -100, 100);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Z-Pos: " + (val >= 0 ? "+" : "") + val),
                    () -> Component.literal("Adjusts right chest depth position")
                ));
                y += 24;

                boolean mirrorPos = MCAInclusiveExpressionsAddon.mirrorPosition;
                this.addRenderableWidget(new ButtonWidget(
                    leftColX, y, fullWidth, 20,
                    Component.literal("Position Symmetry: " + (mirrorPos ? "MIRRORED (Symmetric)" : "INDEPENDENT (Asymmetric)")),
                    b -> {
                        MCAInclusiveExpressionsAddon.mirrorPosition = !MCAInclusiveExpressionsAddon.mirrorPosition;
                        if (MCAInclusiveExpressionsAddon.mirrorPosition) {
                            float lx = 0.0f, ly = 0.0f, lz = 0.0f;
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                                lx = duck.getLeftBreastX(); ly = duck.getLeftBreastY(); lz = duck.getLeftBreastZ();
                                duck.setRightBreastX(-lx); duck.setRightBreastY(ly); duck.setRightBreastZ(lz);
                            }
                            if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                                duck.setRightBreastX(-lx); duck.setRightBreastY(ly); duck.setRightBreastZ(lz);
                            }
                        }
                        refreshPreviewDimensions();
                        this.setPage("breast_addon");
                    }
                ));
            } else if ("rot".equals(breastSubpage)) {
                // --- Sub-Category 3: 3D Rotations ---
                int curLeftPitch = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftPitch = (int) duck.getLeftBreastPitch();
                int curRightPitch = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightPitch = (int) duck.getRightBreastPitch();

                final IntegerSliderWidget[] leftPitchHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightPitchHolder = new IntegerSliderWidget[1];

                leftPitchHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftPitch, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastPitch(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastPitch(angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastPitch(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastPitch(angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && rightPitchHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Pitch (X): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tips left chest up or down")
                ));

                rightPitchHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightPitch, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastPitch(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastPitch(angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastPitch(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastPitch(angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && leftPitchHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Pitch (X): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tips right chest up or down")
                ));
                y += 24;

                int curLeftYaw = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftYaw = (int) duck.getLeftBreastYaw();
                int curRightYaw = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightYaw = (int) duck.getRightBreastYaw();

                final IntegerSliderWidget[] leftYawHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightYawHolder = new IntegerSliderWidget[1];

                leftYawHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftYaw, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastYaw(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastYaw(-angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastYaw(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastYaw(-angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && rightYawHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Yaw (Y): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Swivels left cleavage angle outward/inward")
                ));

                rightYawHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightYaw, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastYaw(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastYaw(-angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastYaw(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastYaw(-angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && leftYawHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Yaw (Y): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Swivels right cleavage angle outward/inward")
                ));
                y += 24;

                int curLeftRoll = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curLeftRoll = (int) duck.getLeftBreastRoll();
                int curRightRoll = 0;
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) curRightRoll = (int) duck.getRightBreastRoll();

                final IntegerSliderWidget[] leftRollHolder = new IntegerSliderWidget[1];
                final IntegerSliderWidget[] rightRollHolder = new IntegerSliderWidget[1];

                leftRollHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, curLeftRoll, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastRoll(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastRoll(-angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setLeftBreastRoll(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setRightBreastRoll(-angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && rightRollHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Left Roll (Z): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tilts left chest sideways left or right")
                ));

                rightRollHolder[0] = this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, curRightRoll, -90, 90,
                    val -> {
                        float angle = (float) val;
                        if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastRoll(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastRoll(-angle);
                        }
                        if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                            duck.setRightBreastRoll(angle);
                            if (MCAInclusiveExpressionsAddon.mirrorRotation) duck.setLeftBreastRoll(-angle);
                        }
                        if (MCAInclusiveExpressionsAddon.mirrorRotation && leftRollHolder[0] instanceof ExtendedSliderWidgetDuck duck) {
                            duck.mca$setIntegerVal(-val, -90, 90);
                        }
                        refreshPreviewDimensions();
                    },
                    val -> Component.literal("Right Roll (Z): " + (val >= 0 ? "+" : "") + val + "°"),
                    () -> Component.literal("Tilts right chest sideways left or right")
                ));
                y += 24;

                boolean mirrorRot = MCAInclusiveExpressionsAddon.mirrorRotation;
                this.addRenderableWidget(new ButtonWidget(
                    leftColX, y, fullWidth, 20,
                    Component.literal("Rotation Symmetry: " + (mirrorRot ? "MIRRORED (Symmetric)" : "INDEPENDENT (Asymmetric)")),
                    b -> {
                        MCAInclusiveExpressionsAddon.mirrorRotation = !MCAInclusiveExpressionsAddon.mirrorRotation;
                        if (MCAInclusiveExpressionsAddon.mirrorRotation) {
                            float lp = 0.0f, ly = 0.0f, lr = 0.0f;
                            if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                                lp = duck.getLeftBreastPitch(); ly = duck.getLeftBreastYaw(); lr = duck.getLeftBreastRoll();
                                duck.setRightBreastPitch(lp); duck.setRightBreastYaw(-ly); duck.setRightBreastRoll(-lr);
                            }
                            if (villagerVisualization != null && villagerVisualization.getGenetics() instanceof GeneticsDuck duck) {
                                duck.setRightBreastPitch(lp); duck.setRightBreastYaw(-ly); duck.setRightBreastRoll(-lr);
                            }
                        }
                        refreshPreviewDimensions();
                        this.setPage("breast_addon");
                    }
                ));
            }
        }
    }
}
