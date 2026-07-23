# Project Version History: MCA Inclusive Expressions Addon

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions Addon project.

---

### Release `1.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.1.0+26.2.jar`
* **Changes**:
  - Integrated ModMenu & YACL config GUI (`ModMenuIntegration`, `ConfigScreenHelper`).
  - Added GameRule `mca_inclusive_expressions:cleavage_angle` (range 0 to 30, default 6 degrees).
  - Updated `CommonVillagerModelMixin` to render dual-mesh outward cleavage rotations.

---

### Release `1.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.0.0+26.2.jar`
* **Changes**:
  - Initial project release with 2.0x chest scaling and gender-inclusive body representation GameRules.
  - Implemented `GeneticsMixin`, `CommonVillagerModelMixin`, `PlayerEntityExtendedModelMixin`, and `PlayerArmorExtendedModelMixin`.
  - Added namespaced GameRules: `mca_inclusive_expressions:scale` and `mca_inclusive_expressions:allow_all_genders` (off by default).
  - Added startup safety guard `ModVersionGuard`.
