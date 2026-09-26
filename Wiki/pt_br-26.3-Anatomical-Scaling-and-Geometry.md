# Escala Anatômica e Geometria (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Isenção de responsabilidade da fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos em desenvolvimento à frente dos lançamentos públicos no CurseForge e Modrinth.

## 1. Official Infobox Table

| Parameter | Technical Specification |
| :--- | :--- |
| **Subsystem Namespace** | `mca_inclusive_expressions_addon:geometry` |
| **Core Java Classes** | `MCAInclusiveExpressionsAddon`, `CommonVillagerInterfaceMixin`, `TorsoClippingVertexConsumer` |
| **Target Models** | `CommonVillagerModel`, `VillagerEntityBaseModelMCA`, `PlayerArmorExtendedModel`, `PlayerEntityExtendedModel` |
| **Scale Bounds** | `0.0x` (completely flat) to `4.44x` (100% slider scale) |
| **Position Bounds** | `X: [-1.0, 1.0]`, `Y: [-1.0, 1.0]`, `Z: [-1.0, 1.0]` (in model block units) |
| **Rotation Bounds** | `Pitch: [-90°, 90°]`, `Yaw: [-90°, 90°]`, `Roll: [-90°, 90°]` |
| **Vertex Clipping Threshold** | Torso wall plane $Z_{\text{wall}} = 1.5 / 16.0\text{f} = 0.09375\text{ blocks}$ |
| **Armor Scaling Factor** | $S_{\text{armor}} = S_{\text{body}} \times 0.55 + 0.20$ |

---

## 2. Step-by-Step Player Workflow

1. **Natural Spawning**:
   - Female villagers automatically sample independent left and right chest volumes according to the Gaussian bell-curve distribution.
   - Male villagers evaluate the server GameRule `full_chested_trait_chance`. If selected, they spawn with the `Full-Chested` trait and receive standard anatomical scaling.
2. **Opening the Editor**:
   - Approach any villager while holding an **Editor Token** or executing `/mca editor` (Creative mode / Admin).
   - Click the **[ Character ]** category tab on the left panel, then select the 5th subpage tab: **[ Breast ]**.
3. **Adjusting Size & Volume**:
   - In the **[ Size ]** sub-category, drag the **Left Size** and **Right Size** sliders from `0%` to `100%`.
   - By default, **Slider Link Mode** is set to `LINKED (Symmetric)`, synchronizing both sides. Click to toggle to `UNLINKED (Asymmetric)` for individual adjustments.
4. **Positioning & Angling**:
   - Switch to **[ Position ]** to adjust horizontal ($X$), vertical ($Y$), and depth ($Z$) placement.
   - Switch to **[ Rotation ]** to adjust Pitch, Yaw, and Roll 3D Euler angles.
5. **Enabling Anti-Back-Poke Guard**:
   - Click **Back-Face Anchor: ON** to clamp internal vertices against the torso back wall.

---

## 3. Mathematical Formulas & Equations

### Gaussian Bell-Curve Volume Sampling
$$X \sim \mathcal{N}(\mu, \sigma^2) \quad \text{where } \mu = 0.225, \; \sigma = 0.075, \; X \in [0.0, 4.44]$$

### Natural Anatomical Asymmetry
$$S_{\text{left}} = \min\left(4.44, \; \max(0.0, \; X + \Delta)\right), \quad S_{\text{right}} = \min\left(4.44, \; \max(0.0, \; X - \Delta)\right)$$
$$\Delta \sim \mathcal{U}(-0.0444, \; +0.0444)$$

### Armor Scaling Compensation
$$S_{\text{armor}} = S_{\text{body}} \times 0.55 + 0.20$$

### Dual-Matrix Inverse Vertex Clamping
$$P_{\text{torso}} = M_{\text{torso}}^{-1} \cdot P_{\text{world}}$$
$$P'_{\text{torso}}.z = \begin{cases} P_{\text{torso}}.z & \text{if } P_{\text{torso}}.z \le Z_{\text{wall}} \\ Z_{\text{wall}} & \text{if } P_{\text{torso}}.z > Z_{\text{wall}} \end{cases}$$
$$P_{\text{final}} = M_{\text{torso}} \cdot P'_{\text{torso}}$$

where $Z_{\text{wall}} = 1.5 / 16.0 = 0.09375\text{ blocks}$.

---

## 4. Visual Transformation Pipeline (ASCII Flowchart)

```
       [ PoseStack: Base Model Root ]
                     |
                     v
       [ Apply Villager Torso Pitch (-35°) ]
                     |
      +--------------+--------------+
      | Capture Torso Matrix (M_torso) |
      +--------------+--------------+
                     |
      +--------------+-----------------------------+
      |                                            |
      v                                            v
[ Left Breast Box Pivot: (-1.75, 0.25, 0.0) ]  [ Right Breast Box Pivot: (+1.75, 0.25, 0.0) ]
      |                                            |
      | 1. Un-rotate -35° pitch (pure world space) | 1. Un-rotate -35° pitch (pure world space)
      | 2. Translate Position (X, Y, Z)            | 2. Translate Position (X, Y, Z)
      | 3. Re-apply -35° pitch                     | 3. Re-apply -35° pitch
      | 4. Translate to Local Pivot Center         | 4. Translate to Local Pivot Center
      | 5. Apply Euler Rotations (Pitch, Yaw, Roll)| 5. Apply Euler Rotations (Pitch, Yaw, Roll)
      | 6. Scale Volume (0.0x to 4.44x)            | 6. Scale Volume (0.0x to 4.44x)
      | 7. Translate Back from Pivot Center        | 7. Translate Back from Pivot Center
      v                                            v
[ TorsoClippingVertexConsumer: Clamped Render ] [ TorsoClippingVertexConsumer: Clamped Render ]
```

---

## 5. Links Rápidos Globais
* [[🌟 Voltar ao Portal MC 26.3|pt_br-26.3-Home]]
* [[🎛️ Editor de Aldeões e Controles Deslizantes|pt_br-26.3-Villager-Editor-and-Sliders]]
* [[🏛️ Arquitetura e Mixins de Bytecode|pt_br-26.3-Architecture-and-Mixins]]
