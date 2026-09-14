// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.widget.ExtendedSliderWidget;
import net.instantgratification.mcainclusive.ducks.ExtendedSliderWidgetDuck;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = ExtendedSliderWidget.class, remap = false)
public abstract class ExtendedSliderWidgetMixin extends AbstractSliderButton implements ExtendedSliderWidgetDuck {
    @Shadow protected Supplier<Component> tooltipSupplier;

    protected ExtendedSliderWidgetMixin(int x, int y, int width, int height, Component message, double value) {
        super(x, y, width, height, message, value);
    }

    @Override
    public void mca$setIntegerVal(int val, int min, int max) {
        if (max > min) {
            double norm = (double) (val - min) / (double) (max - min);
            this.value = Math.clamp(norm, 0.0, 1.0);
            this.updateMessage();
        }
    }

    @Inject(method = "renderTooltip", at = @At("HEAD"), cancellable = true, remap = false)
    private void onRenderTooltip(GuiGraphicsExtractor extractor, int mouseX, int mouseY, CallbackInfo ci) {
        ci.cancel();
        if (this.tooltipSupplier != null) {
            Component text = this.tooltipSupplier.get();
            if (text != null && !text.getString().isEmpty()) {
                this.setTooltip(Tooltip.create(text));
            }
        }
    }
}
