# Project Version History: MCA Inclusive Expressions Addon

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions Addon project.

---

### Release `1.3.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.1+26.2.jar`
* **Changes**:
  - Implemented real-time preview model dimension refresh (`villager.refreshDimensions()`) on slider mutation callbacks.
  - Injected `part.yRot = (float) Math.toRadians(cleavageAngle)` directly into `applyVillagerDimensions`.
  - Added fallback `breastSize` (`0.5f`) for male/neutral villagers when `Gender Inclusivity` is enabled.

---

### Release `1.3.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.0+26.2.jar`
* **Changes**:
  - Added dedicated `Breast` sub-tab under `Character` (`Body`, `Clothes`, `Hair`, `Eyes`, `Breast`) in `VillagerEditorScreen`.

---

### Release `1.2.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.1+26.2.jar`
* **Changes**:
  - Addressed Thinker audit: fixed GUI slider overlap in `VillagerEditorScreen`.

---

### Release `1.2.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.0+26.2.jar`
* **Changes**:
  - Implemented `VillagerEditorScreenMixin` to inject `IntegerSliderWidget` into MCA's `VillagerEditorScreen`.

---

### Release `1.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.1.0+26.2.jar`
* **Changes**:
  - Integrated ModMenu & YACL config GUI (`ModMenuIntegration`, `ConfigScreenHelper`).

---

### Release `1.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.0.0+26.2.jar`
* **Changes**:
  - Initial project release with 2.0x chest scaling and gender-inclusive body representation GameRules.
