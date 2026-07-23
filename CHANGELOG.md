# Changelog - MCA Inclusive Expressions Addon

All notable changes to `MCA Inclusive Expressions Addon` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.2.1+26.2] - 2026-07-23

### Fixed
* Fixed GUI widget overlap in MCA's **Villager Editor Screen** (`VillagerEditorScreen`) by placing `Breast` and `Angle` sliders side-by-side with 4px padding on Row 4.
* Implemented body translation inheritance (`this.getBodyPart().translateAndRotate(matrices)`) for dual-cube cleavage rendering to prevent floating geometry during crouching, sleeping, or riding.

## [1.2.0+26.2] - 2026-07-23

### Added
* Injected a native **Cleavage Angle Slider** directly into MCA's in-game **Villager Editor Screen** (`VillagerEditorScreen`) under the `Character -> Body` tab via `VillagerEditorScreenMixin`.

## [1.1.0+26.2] - 2026-07-23

### Added
* Integrated in-game **ModMenu & YACL Config Screen** (`ModMenuIntegration` and `ConfigScreenHelper`).
* Implemented **Dual-Mesh Outward Cleavage System** with customizable separation angles (`DualBreastModelPart`).
* Added GameRule `mca_inclusive_expressions:cleavage_angle` (default `6` degrees).

## [1.0.0+26.2] - 2026-07-23

### Added
* Initial release of **MCA Inclusive Expressions Addon** for Minecraft 26.2+.
