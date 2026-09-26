# MCA Inclusive Expressions Addon Official Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

Welcome to the official technical documentation and player guide for **MCA Inclusive Expressions**, the definitive Minecraft Comes Alive (MCA Reborn) expansion for anatomical chest customization, independent 3D model scaling, real-time GUI controls, and inclusive character representation.

---

## 🧭 Multi-Version Navigation Portal

Select your target Minecraft version below to enter the dedicated, isolated documentation tree:

| Minecraft Version | Mod Release Version | Loom / Fabric Toolchain | MCA Reborn Parity | Dedicated Version Portal |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | Loom `1.15-SNAPSHOT` / Loader `>=0.16.0` | `8.1.4+26.2` | [[👉 Enter MC 26.2 Wiki|26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | Loom `1.15-SNAPSHOT` / Loader `>=0.16.0` | `8.1.4+26.3` | [[👉 Enter MC 26.3 Wiki|26.3-Home]] |

---

## 🌟 Core Gameplay & Engineering Features

MCA Inclusive Expressions introduces a comprehensive suite of body customization mechanics, advanced MatrixStack 3D transformations, and server-authoritative GameRules:

1. **Independent Dual-Breast 3D MatrixStack Scaling**:
   - Left and right breast model boxes are scaled independently from `0.0x` up to `4.44x` scale.
   - Natural Gaussian bell-curve sampling ($X \sim \mathcal{N}(0.225, 0.075^2)$) generates realistic anatomical variance.
   - Subtly modeled natural asymmetry ($\pm 1\% \to \pm 0.0444$) creates authentic morphological divergence.

2. **Orthogonal 6-Axis Position Customization**:
   - Fully decoupled from MCA's native $-35^\circ$ pitch tilt.
   - True orthogonal translation: *Up is Up, Left is Left, and Forward is Forward*.
   - Slider adjustments range from $-100$ to $+100$ in fixed model units.

3. **3-Axis 3D Euler Rotation Controls**:
   - Full Pitch ($X$), Yaw ($Y$), and Roll ($Z$) manipulation ($-90^\circ$ to $+90^\circ$).
   - Symmetric mirroring or independent asymmetric angling.

4. **TorsoClippingVertexConsumer (Anti-Back-Poke Clipping Engine)**:
   - Eliminates back-poking vertices inside the torso when scaling breasts to large sizes.
   - Uses dual-matrix inverse transformation ($M_{\text{torso}}^{-1} \cdot P$) to clamp vertices strictly at the torso wall ($Z \le 1.5/16$).

5. **Integrated Villager Editor Screen GUI**:
   - Adds a dedicated 5th character subpage tab: **[ Breast ]**.
   - Nested sub-category tabs: **[ Size ]**, **[ Position ]**, **[ Rotation ]**.
   - Symmetry mode toggles: Slider Linking, Position Mirroring, Rotation Mirroring, and Back-Face Anchoring.

6. **Dynamic Trait Registration & GameRules**:
   - Injects the `full_chested` trait into MCA's trait registry with a $+0.5$ scale bonus.
   - Server-authoritative GameRules: `mca_inclusive_expressions:force_all_breasted` and `mca_inclusive_expressions:full_chested_trait_chance`.

---

## 📚 Global Architecture & Reference Guides

- [[Version Compatibility & Matrix|Version-Compatibility]]: Supported Minecraft versions, Fabric Loader bounds, and DasikLibrary `>=1.8.36`.
- [[Troubleshooting & FAQ|Troubleshooting-and-FAQ]]: Armor clipping solutions, trait inheritance rules, NBT persistence, and server permissions.
- [[Developer Setup & Building|Developer-Setup-and-Building]]: Unified Gradle 9.3+, Fabric Loom 1.15-SNAPSHOT, and JDK 25 compilation guide.

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
