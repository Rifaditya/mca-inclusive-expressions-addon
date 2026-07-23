// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
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

    protected VillagerEditorScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "setPage", at = @At("TAIL"))
    private void onSetPageTail(String page, CallbackInfo ci) {
        if ("body".equals(page)) {
            int leftX = this.width / 2 + DATA_WIDTH / 2 + 2;
            int y = this.height / 2 - 80 + 24 + 4 + 24; // Row 4 right-half position
            int widgetWidth = DATA_WIDTH / 2 - 2;

            int currentAngle = MCAInclusiveExpressionsAddon.getCleavageAngle();

            this.addRenderableWidget(new IntegerSliderWidget(
                leftX,
                y,
                widgetWidth,
                20,
                currentAngle,
                0,
                30,
                val -> MCAInclusiveExpressionsAddon.defaultCleavageAngle = val,
                val -> Component.literal("Angle: " + val + "°"),
                () -> Component.literal("Adjusts outward cleavage separation angle for dual-mesh breasts")
            ));
        }
    }
}
