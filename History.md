# Project Version History: MCA Inclusive Expressions Addon

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions Addon project.

---

### Release `1.2.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.1+26.2.jar`
* **Changes**:
  - Addressed Thinker audit: fixed GUI slider overlap in `VillagerEditorScreen` using side-by-side Row 4 placement with 4px gap padding.
  - Added `getBodyPart().translateAndRotate(matrices)` matrix stack inheritance to `CommonVillagerModelMixin`.

---

### Release `1.2.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.0+26.2.jar`
* **Changes**:
  - Implemented `VillagerEditorScreenMixin` to inject `IntegerSliderWidget` into MCA's `VillagerEditorScreen` under `Character -> Body`.

---

### Release `1.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.1.0+26.2.jar`
* **Changes**:
  - Integrated ModMenu & YACL config GUI (`ModMenuIntegration`, `ConfigScreenHelper`).
  - Added clean-room `DualBreastModelPart`.

---

### Release `1.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.0.0+26.2.jar`
* **Changes**:
  - Initial project release with 2.0x chest scaling and gender-inclusive body representation GameRules.
