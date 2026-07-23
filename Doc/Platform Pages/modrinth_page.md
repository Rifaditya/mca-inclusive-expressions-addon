<p align="center">
  <h1 align="center">MCA Inclusive Expressions</h1>
  <p align="center"><b>Core MCA Enhancement Mod for Minecraft Comes Alive Reborn</b></p>
</p>

<p align="center">
  <a href="https://fabricmc.net/"><img src="https://img.shields.io/badge/Loader-Fabric-dbb084?style=for-the-badge" alt="Fabric"></a>
  <a href="https://www.minecraft.net/"><img src="https://img.shields.io/badge/Minecraft-26.2%2B-5b8731?style=for-the-badge" alt="Minecraft 26.2+"></a>
  <a href="https://www.gnu.org/licenses/gpl-3.0.html"><img src="https://img.shields.io/badge/License-GPLv3-blue?style=for-the-badge" alt="GPLv3"></a>
</p>

---

## 🌟 What is MCA Inclusive Expressions?

**MCA Inclusive Expressions** is a primary **Core Enhancement Mod** for **MCA Reborn (Minecraft Comes Alive)**. It deeply refactors MCA's genetics system, 3D character renderer, and in-game editor interface to provide **100% independent left and right 3D breast model part scaling**, asymmetric customization, and inclusive body feature options for all villager gender expressions.

> **Active Version Policy**: Built natively for **Minecraft 26.2+ (Fabric Loader 0.16.0+)**. Requires MCA Reborn.

---

## ⚡ Key Features

### 📐 Independent 3D Matrix Model Part Scaling
Replaces MCA's default single unified chest box with **two distinct 3D cuboid model parts** (`left_breast` and `right_breast`). Each cube scales outward from its own independent center point using custom MatrixStack transformations, guaranteeing **true 3D visual asymmetry** and preserving a natural cleavage gap at all size settings!

### 🎨 Dedicated In-Game Breast Sub-Tab
Adds a clean, dedicated **`Breast` Sub-Tab** directly inside MCA's **Villager Editor Screen** (`Character -> Body, Clothes, Hair, Eyes, Breast`):
- **Left Breast Size Slider**: `0%` to `Max Limit%` (Standard default `100%`).
- **Right Breast Size Slider**: `0%` to `Max Limit%` (Standard default `100%`).
- **Slider Link Mode Toggle**: Easily switch between `LINKED (Symmetric)` and `UNLINKED (Asymmetric)` scaling.
- **Max Scale Limit Toggle Button**: In-GUI toggle cycling max scale range (`200% Default`, `300%`, `500%`, `1000%`).
- **Gender Representation Inclusivity Toggle**: Enable or disable chest scaling features across all villager gender expressions.

### 🔍 Real-Time 3D Preview
All slider adjustments immediately update the 3D villager model in the editor preview screen in real time with zero delay or screen reloads.

---

## ⚙️ Configuration & GUI

Fully compatible with **ModMenu** and **YetAnotherConfigLib (YACL)**. Server host parameters can also be configured via namespaced GameRules:
- `/gamerule mca_inclusive_expressions:scale 100` (Default chest scale multiplier)
- `/gamerule mca_inclusive_expressions:allow_all_genders true` (Enable gender inclusivity globally)

---

## 📜 Credits & License

| Role | Author |
| :--- | :--- |
| **Lead Developer** | **Dasik (Rifaditya)** |
| **Target Mod Platform** | **MCA Reborn Team (Conczin)** |

Licensed under the **GNU General Public License v3.0 (GPLv3)**. Included in modpacks freely under GPLv3 rules.
