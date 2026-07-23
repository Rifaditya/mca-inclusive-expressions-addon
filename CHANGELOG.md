# Changelog - MCA Inclusive Expressions Addon

All notable changes to `MCA Inclusive Expressions Addon` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.6.4+26.2] - 2026-07-23

### Fixed
* Assigned separate `PartPose` pivot offsets to `left_breast` (`X = -1.75F`) and `right_breast` (`X = +1.75F`), ensuring each breast cube scales outward from its own center.
* Preserved a permanent cleavage separation gap so breasts **ALWAYS render as 2 distinct 3D cubes** under all scale sizes (`0%` to `1000%`).

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
