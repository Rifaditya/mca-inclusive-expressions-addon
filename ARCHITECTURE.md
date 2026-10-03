# Architecture & Symbol Index: MCA Inclusive Expressions

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `mca_inclusive_expressions_addon`
- **Main Entrypoint**: `net.instantgratification.mcainclusive.MCAInclusiveExpressionsAddon` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `None`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.GeneticsMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.CommonVillagerModelMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.CommonVillagerInterfaceMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.ModelPartAccessor` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.PlayerEntityExtendedModelMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.PlayerArmorExtendedModelMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerVisualsMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerRenderStateMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerEditorSyncRequestMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerEntityMCAMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerDimensionsMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.VillagerVisualsRecordMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.mcainclusive.mixin.TraitsMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`mca_inclusive_expressions_addon:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
