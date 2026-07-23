# MCA Inclusive Expressions Addon

An **Instant Gratification (IG)** collection addon for **MCA Reborn (Minecraft Comes Alive)** targeting Minecraft 26.2+.

## Features
* **Expanded Character Customization Scaling**: Multiplies female character chest feature scaling by `2.0x` over baseline MCA defaults.
* **Gender-Inclusive Representation Toggle**: Includes a GameRule allowing male and neutral characters to feature chest scaling and genetics when enabled by server admins (disabled by default for standard MCA behavior).
* **Seamless Overlay Scaling**: Dynamically shifts position origins ($Y, Z$) so clothing, jacket, and armor layers stretch cleanly without Z-fighting or torso mesh clipping.

## Configurable GameRules
* `/gamerule mca_inclusive_expressions:scale <value>`: Sets scaling multiplier (`200` = 2.0x, range `10` to `1000`).
* `/gamerule mca_inclusive_expressions:allow_all_genders <true|false>`: Toggles gender-inclusive chest scaling for all genders (default `false`).

## Requirements
* Minecraft `26.2+`
* Fabric Loader `>=0.16.0`
* MCA Reborn (Minecraft Comes Alive) `>=8.0.0`

## License
Copyright (C) 2026 Dasik (Rifaditya) | GNU General Public License v3.0 (GPLv3)
