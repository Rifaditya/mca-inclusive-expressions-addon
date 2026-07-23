# Project Version History: MCA Inclusive Expressions

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions project.

---

### Release `2.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.0+26.2.jar`
* **Changes**:
  - Organized the Breast tab into 3 nested sub-categories ([ Size ], [ Position ], [ Rotation ]).
  - Added 3-axis 3D Euler angle rotation sliders (Pitch X, Yaw Y, Roll Z) for Left & Right breasts with NBT persistence and MatrixStack quaternion transformation.

---

### Release `2.0.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.2+26.2.jar`
* **Changes**:
  - Implemented local pivot-centered matrix stack transformations ((-1.75f, 0.25f, 0.0f) for Left and (+1.75f, 0.25f, 0.0f) for Right) so Size sliders modify volume ONLY with zero position drift across the body.

---

### Release `2.0.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.1+26.2.jar`
* **Changes**:
  - Calibrated 1:1 normal scale calculation, removing MCA's internal getBreastSize() * getBreasts() sub-fraction multiplication (0.05f) so 99%/100% renders at full 1.0x normal size and 500% renders at 5.0x massive size.

---

### Release `2.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.0+26.2.jar`
* **Changes**:
  - Implemented direct 1:1 linear size matrix scaling (100% = 1.0x, 500% = 5.0x, 1000% = 10.0x).
  - Migrated Max Scale Limit setting out of the character GUI into a server GameRule (mca_inclusive_expressions:max_scale_limit, range 100-2000, default 500%).
  - Cleaned up Row 5 in character editor GUI by expanding Slider Link Mode button to full width.

---

### Release `1.9.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.9.0+26.2.jar`
* **Changes**:
  - Implemented VillagerRenderStateDuck to transfer 8 3D parameters per villager into VillagerRenderState, eliminating global static variable pollution during rendering.
  - Added 6 independent 3D position sliders (Left X/Y/Z and Right X/Y/Z) in a 2-column 6-row GUI layout.
  - Restored MCA native chest alignment.

---

### Release `1.8.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.8.5+26.2.jar`
* **Changes**:
  - Centered 3D MatrixStack scaling around Left Breast's pivot center (-1.75F, 0.25F, 0.0F) and Right Breast's pivot center (+1.75F, 0.25F, 0.0F) for 100% total 3D mesh isolation.

---

### Release `1.8.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.8.0+26.2.jar`
* **Changes**:
  - Implemented GeneticsDuck interface on MCA's Genetics class to give every villager two 100% independent genetic breast properties (leftBreastSize and rightBreastSize) with full NBT persistence, genetic inheritance, and independent 3D MatrixStack scaling.

---

### Release `1.7.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.5+26.2.jar`
* **Changes**:
  - Restored MCA's native -35 degree breast rotation angle via part.translateAndRotate(matrices) and scaled cubes.get(0) with leftBreastSize matrix and cubes.get(1) with rightBreastSize matrix.

---

### Release `1.7.4+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.4+26.2.jar`
* **Changes**:
  - Fixed method signature descriptor of onBodyData in VillagerEntityModelMixin, resolving InvalidInjectionException on startup.

---

### Release `1.7.3+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.3+26.2.jar`
* **Changes**:
  - Implemented failsafe dual breast children architecture (breasts.getChild("left") and breasts.getChild("right")) across VillagerEntityBaseModelMCA and VillagerEntityModelMCA with automatic fallback rendering.

---

### Release `1.7.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.2+26.2.jar`
* **Changes**:
  - Implemented CommonVillagerModelAccess interface and bound leftBreastPart and rightBreastPart to renderCommon for true 100% independent left and right 3D cube scaling.

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
