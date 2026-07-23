# Changelog - MCA Inclusive Expressions Addon

All notable changes to `MCA Inclusive Expressions Addon` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0+26.2] - 2026-07-23

### Added
* Initial release of **MCA Inclusive Expressions Addon** for Minecraft 26.2+.
* Dynamic 2.0x breast/chest scaling multiplier for MCA character models.
* Gender-inclusive body customization feature allowing male and neutral characters to feature chest scaling when enabled by server admins.
* Registered GameRules:
  - `mca_inclusive_expressions:scale` (default `200` = 2.0x, range `10` to `1000`).
  - `mca_inclusive_expressions:allow_all_genders` (boolean, default **`false`** — OFF by default).
* Added zero-dependency `ModVersionGuard` check on startup.
