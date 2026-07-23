# Changelog - MCA Inclusive Expressions Addon

All notable changes to `MCA Inclusive Expressions Addon` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.3.1+26.2] - 2026-07-23

### Fixed
* Fixed real-time 3D model preview rendering in MCA's **Villager Editor Screen** (`VillagerEditorScreen`) by adding `villager.refreshDimensions()` calls to slider mutation callbacks.
* Injected native `ModelPart.yRot` cleavage angle transformation directly into `CommonVillagerModel.applyVillagerDimensions`.
* Fixed fallback `breastSize` (`0.5f`) for male/neutral villagers when `Gender Inclusivity` is enabled.

## [1.3.0+26.2] - 2026-07-23

### Added
* Added a dedicated **`Breast` Sub-Tab** under `Character` (`Body`, `Clothes`, `Hair`, `Eyes`, `Breast`) in MCA's **Villager Editor Screen** (`VillagerEditorScreen`).

## [1.2.1+26.2] - 2026-07-23

### Fixed
* Fixed GUI widget overlap in MCA's **Villager Editor Screen** (`VillagerEditorScreen`).

## [1.2.0+26.2] - 2026-07-23

### Added
* Injected a native **Cleavage Angle Slider** directly into MCA's in-game **Villager Editor Screen**.

## [1.1.0+26.2] - 2026-07-23

### Added
* Integrated in-game **ModMenu & YACL Config Screen**.

## [1.0.0+26.2] - 2026-07-23

### Added
* Initial release of **MCA Inclusive Expressions Addon** for Minecraft 26.2+.
