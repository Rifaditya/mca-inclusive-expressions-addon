// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.PlayerEntityExtendedModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = PlayerEntityExtendedModel.class, remap = false)
public abstract class PlayerEntityExtendedModelMixin {
    // PlayerEntityExtendedModel implements CommonVillagerModel and is managed via CommonVillagerInterfaceMixin!
}
