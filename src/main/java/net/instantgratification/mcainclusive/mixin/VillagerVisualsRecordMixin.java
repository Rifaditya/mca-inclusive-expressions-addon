// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.render.VillagerVisuals;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = VillagerVisuals.class, remap = false)
public abstract class VillagerVisualsRecordMixin {
    // Male identity preservation: female() record field returns true gender without forcing male entities into female visual textures/clothes.
}
