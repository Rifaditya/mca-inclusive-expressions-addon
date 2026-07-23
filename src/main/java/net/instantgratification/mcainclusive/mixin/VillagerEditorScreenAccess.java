// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.mixin;

import net.conczin.mca.client.gui.VillagerEditorScreen;
import net.conczin.mca.entity.VillagerEntityMCA;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = VillagerEditorScreen.class, remap = false)
public interface VillagerEditorScreenAccess {
    @Accessor(value = "villager", remap = false)
    VillagerEntityMCA getVillager();

    @Accessor(value = "villagerVisualization", remap = false)
    VillagerEntityMCA getVillagerVisualization();
}
