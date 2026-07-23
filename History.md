# Project Version History: MCA Inclusive Expressions

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions project.

---

### Release `1.7.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.1+26.2.jar`
* **Changes**:
  - Resolved startup InvalidInjectionException by creating CommonVillagerInterfaceMixin targeting CommonVillagerModel interface for renderCommon.

---

### Release `1.7.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.0+26.2.jar`
* **Changes**:
  - Upgraded to primary MCA Core Enhancement Mod (`MCA Inclusive Expressions`).

---

### Release `1.6.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.5+26.2.jar`
* **Changes**:
  - Injected directly into static `VillagerEntityBaseModelMCA.newBreasts` to bake 2 distinct 3x3x3 cubes across all model subclasses (`VillagerEntityModelMCA`, `PlayerEntityExtendedModel`, armor meshes).

---

### Release `1.6.4+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.4+26.2.jar`
* **Changes**:
  - Assigned separate pivot offsets to left_breast (X = -1.75F) and right_breast (X = +1.75F), maintaining a permanent cleavage gap and preventing mesh merging at 200%+ scales.

---

### Release `1.6.3+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.3+26.2.jar`
* **Changes**:
  - Fixed render gate condition in `CommonVillagerModelMixin` by keeping container `breasts.visible = true`.

---

### Release `1.6.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.2+26.2.jar`
* **Changes**:
  - Implemented independent 3D ModelParts (`left_breast` & `right_breast`) for true 100% individual cube scaling.

---

### Release `1.6.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.1+26.2.jar`
* **Changes**:
  - Injected into `VillagerEntityBaseModelMCA.newBreasts` to split MCA's single 6x3x3 box into two distinct 3x3x3 cubes (Left & Right Breast) for true 2-rectangle 3D model rendering.

---

### Release `1.6.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.0+26.2.jar`
* **Changes**:
  - Implemented independent Left Breast & Right Breast controls with linked/unlinked asymmetry modes.
  - Set default slider range to 0%-200% (default 100%).
  - Added customizable Max Scale Limit toggle button (200%, 300%, 500%, 1000%).

---

### Release `1.5.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.5.1+26.2.jar`
* **Changes**:
  - Extended Breast Size slider minimum range from 10% to 0% (0% to 1000%) across GUI, GameRules, and ModMenu config screen for flat chest customization.

---

### Release `1.5.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.5.0+26.2.jar`
* **Changes**:
  - Unified base genetics `Breast` slider and `Chest Scale` multiplier into ONE single `Breast Size: X%` slider (10% to 1000%) in the `Breast` sub-tab.

---

### Release `1.4.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.4.1+26.2.jar`
* **Changes**:
  - Consolidated Base Breast Gene Size, Chest Scale Multiplier %, and Gender Inclusivity controls into the dedicated `Breast` sub-tab in `VillagerEditorScreen`.

---

### Release `1.4.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.4.0+26.2.jar`
* **Changes**:
  - Redid and streamlined core codebase: removed dual breast models and cleavage angle calculations.

---

### Release `1.3.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.1+26.2.jar`
* **Changes**:
  - Implemented real-time preview model dimension refresh (`villager.refreshDimensions()`).

---

### Release `1.3.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.0+26.2.jar`
* **Changes**:
  - Added dedicated `Breast` sub-tab under `Character` (`Body`, `Clothes`, `Hair`, `Eyes`, `Breast`).

---

### Release `1.2.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.1+26.2.jar`
* **Changes**:
  - Fixed GUI slider overlap in `VillagerEditorScreen`.

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
