# Changelog - MCA Inclusive Expressions

All notable changes to `MCA Inclusive Expressions` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [2.1.4+26.2] - 2026-07-23

### Fixed
* **Accessor Mixin Architecture**: Created `@Accessor` interface `VillagerEditorScreenAccess` and moved `getActiveGuiGenetics()` helper to `MCAInclusiveExpressionsAddon`, removing all non-private static members from `VillagerEditorScreenMixin` and resolving `InvalidMixinException` startup crash.

## [2.1.3+26.2] - 2026-07-23

### Fixed
* **Mixin Static Field Crash Fix**: Relocated `activeEditorScreen` reference to `MCAInclusiveExpressionsAddon`, satisfying Fabric Mixin static field rules and resolving `InvalidMixinException` startup crash.

## [2.1.2+26.2] - 2026-07-23

### Fixed
* **Direct GUI Screen Genetics Access**: Implemented live GUI screen genetics override in `CommonVillagerInterfaceMixin.onRenderCommon`, allowing the renderer to read 3D parameters directly from `VillagerEditorScreen`'s active genetics during GUI rendering. This provides 100% real-time 1:1 synchronization between GUI sliders, the 3D preview model, and the in-world entity!

## [2.1.1+26.2] - 2026-07-23

### Fixed
* **GUI Real-Time Preview Synchronization**: Implemented automatic genetics parameter synchronization from `villager` to `villagerVisualization` upon GUI screen initialization (`init` & `setPage`), eliminating the visual disconnect between real in-world entity size and GUI 3D preview model size.

## [2.1.0+26.2] - 2026-07-23

### Added
* **Nested Sub-Categories (`[ Size ]`, `[ Position ]`, `[ Rotation ]`)**: Organized the `Breast` tab into 3 spacious, un-crowded nested sub-pages, eliminating GUI clutter and widget clipping.
* **3-Axis 3D Euler Rotation Sliders (Pitch X, Yaw Y, Roll Z)**: Added independent Pitch (Tip Up/Down: $-90^\circ$ to $+90^\circ$), Yaw (Cleavage Swivel Out/In: $-90^\circ$ to $+90^\circ$), and Roll (Side Tilt: $-90^\circ$ to $+90^\circ$) rotation sliders for Left & Right breasts.
* Full NBT persistence (`mca_inclusive_expressions:left/right_breast_pitch/yaw/roll`) and MatrixStack quaternion transformation engine.

## [2.0.2+26.2] - 2026-07-23

### Fixed
* **Total Size and Position Isolation**: Implemented local pivot-centered matrix stack transformations (`(-1.75f, 0.25f, 0.0f)` for Left and `(+1.75f, 0.25f, 0.0f)` for Right) so Size sliders modify volume ONLY with zero position drift across the body.

## [2.0.1+26.2] - 2026-07-23

### Fixed
* **Calibrated 1:1 Normal Scale Calibration**: Eliminated MCA's internal `getBreastSize() * getBreasts()` sub-fraction multiplication (`0.25f * 0.20f = 0.05f`) so that `99%` / `100%` renders at full 1.0x normal MCA size, `200%` renders at 2.0x, and `500%` renders at 5.0x massive size.

## [2.0.0+26.2] - 2026-07-23

### Changed
* **Direct 1:1 Linear Size Scaling**: Replaced legacy compression formula (`size * 0.2f + 1.05f`) with true 1:1 direct linear matrix scaling (`100% = 1.0x`, `200% = 2.0x`, `500% = 5.0x`, `1000% = 10.0x`).
* **GameRule-Only Max Limit**: Migrated Max Scale Limit setting out of the character GUI into a server GameRule (`mca_inclusive_expressions:max_scale_limit`, range 100-2000, default 500%).
* Cleaned up Row 5 in character editor GUI by expanding `Slider Link Mode` button to full width.

## [1.9.0+26.2] - 2026-07-23

### Added
* Attached `VillagerRenderStateDuck` to MCA's `VillagerRenderState` to transfer all 8 3D parameters (`leftSize`, `rightSize`, `leftX`, `leftY`, `leftZ`, `rightX`, `rightY`, `rightZ`) per villager, eliminating global static variable pollution during rendering.
* Added **6 independent 3D position sliders** (Left X/Y/Z and Right X/Y/Z) in a clean 2-column GUI layout inside the `Breast` sub-tab.
* Restored MCA's native chest default alignment.

## [1.8.5+26.2] - 2026-07-23

### Fixed
* Centered 3D MatrixStack scaling around Left Breast's pivot center `(-1.75F, 0.25F, 0.0F)` and Right Breast's pivot center `(+1.75F, 0.25F, 0.0F)` for **100% total 3D mesh isolation**.
* Added independent X-position offset translations for left and right breasts.

## [1.8.0+26.2] - 2026-07-23

### Added
* Implemented `GeneticsDuck` interface on MCA's `Genetics` class to give every villager **two 100% independent genetic breast properties (`leftBreastSize` and `rightBreastSize`)**.
* Added NBT serialization (`mca_inclusive_expressions:breast_left` and `mca_inclusive_expressions:breast_right`) for permanent world storage.
* Connected Left Breast and Right Breast sliders to `GeneticsDuck` for **true independent 3D asymmetric scaling and genetic inheritance**!

## [1.7.5+26.2] - 2026-07-23

### Fixed
* Restored MCA's native **-35° breast rotation angle** via `part.translateAndRotate(matrices)`.
* Scaled `cubes.get(0)` (Left Breast) with `leftBreastSize` MatrixStack scaling and `cubes.get(1)` (Right Breast) with `rightBreastSize` MatrixStack scaling for **100% independent left and right 3D cube scaling**!

## [1.7.4+26.2] - 2026-07-23

### Fixed
* Corrected method descriptor for `onBodyData` in `VillagerEntityModelMixin` to `(CubeDeformation)` resolving `InvalidInjectionException` startup crash.

## [1.7.3+26.2] - 2026-07-23

### Fixed
* Attached `"left"` and `"right"` child `ModelPart` instances directly inside `breasts` container across `VillagerEntityBaseModelMCA` and `VillagerEntityModelMCA` model bakes.

## [1.7.2+26.2] - 2026-07-23

### Fixed
* Created `CommonVillagerModelAccess` interface and registered distinct `left_breast` and `right_breast` `ModelPart` instances.

## [1.7.1+26.2] - 2026-07-23

### Fixed
* Created `CommonVillagerInterfaceMixin` targeting `CommonVillagerModel` interface to resolve `InvalidInjectionException` startup crash.

## [1.7.0+26.2] - 2026-07-23

### Added
* Upgraded addon to a primary **MCA Core Enhancement Mod** (`MCA Inclusive Expressions`).

## [1.6.5+26.2] - 2026-07-23

### Fixed
* Restored static `@Inject` into `VillagerEntityBaseModelMCA.newBreasts` at `@At("HEAD")`.

## [1.6.4+26.2] - 2026-07-23

### Fixed
* Assigned separate `PartPose` pivot offsets to `left_breast` (`X = -1.75F`) and `right_breast` (`X = +1.75F`).

## [1.6.3+26.2] - 2026-07-23

### Fixed
* Fixed render gate condition in `CommonVillagerModelMixin` by ensuring container `breasts.visible` remains `true`.

## [1.6.2+26.2] - 2026-07-23

### Changed
* Created separate `left_breast` and `right_breast` `ModelPart` instances.

## [1.6.1+26.2] - 2026-07-23

### Changed
* Injected into `VillagerEntityBaseModelMCA.newBreasts` to split MCA's single 6x3x3 chest box into **two distinct 3x3x3 cubes**.

## [1.6.0+26.2] - 2026-07-23

### Added
* Split chest models into independent **Left Breast** and **Right Breast** controls with linked/unlinked asymmetric customization.
* Set default slider range to **`0%` to `200%`** (standard size `100%`).
* Added in-GUI **Max Scale Limit** toggle button (`200%`, `300%`, `500%`, `1000%`).

## [1.5.1+26.2] - 2026-07-23

### Changed
* Extended **`Breast Size: X%`** slider minimum range from `10%` to **`0%`** (`0%` to `1000%`).

## [1.5.0+26.2] - 2026-07-23

### Changed
* Unified base genetics `Breast` gene slider and `Chest Scale` multiplier into **ONE single `Breast Size: X%` slider** (10% to 1000%) inside the dedicated **`Breast` Sub-Tab**.

## [1.4.1+26.2] - 2026-07-23

### Changed
* Removed duplicate `Breast` gene slider from MCA's `Body` sub-tab.

## [1.4.0+26.2] - 2026-07-23

### Changed
* Streamlined and redone addon architecture: removed dual breast models and cleavage angle calculations.

## [1.3.1+26.2] - 2026-07-23

### Fixed
* Fixed real-time 3D model preview rendering in MCA's **Villager Editor Screen**.

## [1.3.0+26.2] - 2026-07-23

### Added
* Added a dedicated **`Breast` Sub-Tab** under `Character` (`Body`, `Clothes`, `Hair`, `Eyes`, `Breast`).

## [1.2.1+26.2] - 2026-07-23

### Fixed
* Fixed GUI widget overlap in MCA's **Villager Editor Screen**.

## [1.2.0+26.2] - 2026-07-23

### Added
* Injected a native **Cleavage Angle Slider** directly into MCA's in-game **Villager Editor Screen**.

## [1.1.0+26.2] - 2026-07-23

### Added
* Integrated in-game **ModMenu & YACL Config Screen**.

## [1.0.0+26.2] - 2026-07-23

### Added
* Initial release of **MCA Inclusive Expressions Addon** for Minecraft 26.2+.
