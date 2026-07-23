// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.model.PlayerArmorExtendedModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = PlayerArmorExtendedModel.class, remap = false)
public abstract class PlayerArmorExtendedModelMixin {
    // PlayerArmorExtendedModel implements CommonVillagerModel and is managed via CommonVillagerInterfaceMixin and copyCommonAttributes!
}
