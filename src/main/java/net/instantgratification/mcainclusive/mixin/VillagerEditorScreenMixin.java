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
            // Fail-safe: Check if Full-Chested button was rendered natively by MCA
            boolean alreadyRendered = false;
            for (var child : this.children()) {
                if (child instanceof AbstractWidget widget && widget.getMessage() != null) {
                    if (widget.getMessage().getString().contains("Full-Chested")) {
                        alreadyRendered = true;
                        break;
                    }
                }
            }
            if (!alreadyRendered) {
                boolean isPlayer = villagerUUID != null && villagerUUID.equals(playerUUID);
                int leftColX = this.width / 2;
                int targetSlotY = -1;

                if (isPlayer && this.traitPage == 1) { // Page 2 for Player Characters
                    targetSlotY = 64 + (4 * 22); // Y = 152
                } else if (!isPlayer && this.traitPage == 2) { // Page 3 for NPC Villagers
                    targetSlotY = 64 + (5 * 22); // Y = 174
                }

                if (targetSlotY > 0) {
                    boolean hasFullChested = false;
                    if (villager != null && villager.getTraits() != null && MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT != null) {
                        hasFullChested = villager.getTraits().hasTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                    }
                    Component label = Component.literal("Full-Chested").withStyle(hasFullChested ? net.minecraft.ChatFormatting.GREEN : net.minecraft.ChatFormatting.GRAY);
                    this.addRenderableWidget(new ButtonWidget(
                        leftColX, targetSlotY, DATA_WIDTH, 20,
                        label,
                        b -> {
                            if (villager != null && villager.getTraits() != null && MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT != null) {
                                if (villager.getTraits().hasTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT)) {
                                    villager.getTraits().removeTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                                    b.setMessage(Component.literal("Full-Chested").withStyle(net.minecraft.ChatFormatting.GRAY));
                                } else {
                                    villager.getTraits().addTrait(MCAInclusiveExpressionsAddon.FULL_CHESTED_TRAIT);
                                    b.setMessage(Component.literal("Full-Chested").withStyle(net.minecraft.ChatFormatting.GREEN));
                                }
                                refreshPreviewDimensions();
                            }
                        }
                    ));
                }
            }
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
                // --- Sub-Category 1: Size (Direct Linear Mapping: 0% = 0.0, 20% = 1.0, 100% = 5.0) ---
                int currentLeftPct = 20; // 20% maps to 1.0x native default scale
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                    float val = duck.getLeftBreastSize();
                    currentLeftPct = val > 0 ? (int) (val / 5.0f * 100.0f) : 20;
                }
                this.addRenderableWidget(new IntegerSliderWidget(
                    leftColX, y, halfWidth, 20, currentLeftPct, 0, maxLimit,
                    val -> {
                        float scale = (val / 100.0f) * 5.0f; // Direct linear scale up to 5.0
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
                    () -> Component.literal("Adjusts left chest volume (100% = 5.0x max scale)")
                ));

                int currentRightPct = 20; // 20% maps to 1.0x native default scale
                if (villager != null && villager.getGenetics() instanceof GeneticsDuck duck) {
                    float val = duck.getRightBreastSize();
                    currentRightPct = val > 0 ? (int) (val / 5.0f * 100.0f) : 20;
                }
                this.addRenderableWidget(new IntegerSliderWidget(
                    rightColX, y, halfWidth, 20, currentRightPct, 0, maxLimit,
                    val -> {
                        float scale = (val / 100.0f) * 5.0f; // Direct linear scale up to 5.0
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
                    () -> Component.literal("Adjusts right chest volume (100% = 5.0x max scale)")
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
