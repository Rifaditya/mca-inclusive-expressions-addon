# 村民編輯器與互動滑桿 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼倉庫來源免責聲明**：本維基文件反映**程式碼倉庫當前的最新原始碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

## 1. Official Infobox Table

| Parameter | Technical Specification |
| :--- | :--- |
| **GUI Class** | `net.conczin.mca.client.gui.VillagerEditorScreen` |
| **Injected Subpage Key** | `"breast_addon"` |
| **Subpage Button Title** | `"Breast"` |
| **Nested Sub-Categories** | `[ Size ]` (`"size"`), `[ Position ]` (`"pos"`), `[ Rotation ]` (`"rot"`) |
| **Max Scale Limit** | `100%` (Direct linear mapping to `4.44x` scale) |
| **Position Slider Range** | `[-100, 100]` (Direct offset multiplier $\times 0.01$) |
| **Rotation Slider Range** | `[-90°, +90°]` (Degrees along $X$, $Y$, $Z$ axes) |
| **Network Sync Packet** | `VillagerEditorSyncRequest` (C2S) |

---

## 2. In-Game Editor Navigation Workflow

1. **Accessing the Editor**:
   - Right-click a villager with an Editor Token or run `/mca editor`.
   - The left sidebar displays main categories: **[ Character ]**, **[ Profession ]**, **[ Inventory ]**, etc.
2. **Selecting the Breast Subpage**:
   - Under **[ Character ]**, select the 5th subpage tab: **[ Breast ]**.
3. **Navigating the 3 Sub-Categories**:
   - **[ Size ]**: Independent Left and Right volume sliders (`0%` to `100%`).
   - **[ Position ]**: Horizontal ($X$), Vertical ($Y$), and Depth ($Z$) position sliders.
   - **[ Rotation ]**: Pitch ($X$), Yaw ($Y$), and Roll ($Z$) 3D Euler angle sliders.
4. **Toggling Symmetry Link Modes**:
   - **Slider Link Mode**: `LINKED (Symmetric)` vs `UNLINKED (Asymmetric)`.
   - **Position Symmetry**: `MIRRORED` vs `INDEPENDENT`.
   - **Back-Face Anchor**: Toggles real-time vertex clamping against torso wall.

---

## 3. Mathematical Mapping Formulas

$$S = \left(\frac{V_{\text{slider}}}{100}\right) \times 4.44$$

$$T = \frac{P_{\text{slider}}}{100.0} \quad (\text{blocks})$$

$$\theta_{\text{rad}} = R_{\text{slider}} \times \frac{\pi}{180}$$

---

## 4. Editor GUI Hierarchy (ASCII Diagram)

```
+-----------------------------------------------------------------------------------+
|  VILLAGER EDITOR: [ Character ]                                                   |
+-----------------------------------------------------------------------------------+
|  [ Body ] | [ Clothing Style ] | [ Hair Style ] | [ Eyes ] | [ Breast (Active) ]  |
+-----------------------------------------------------------------------------------+
|  Sub-Categories: [ Size ] | [ Position ] | [ Rotation ]                           |
+-----------------------------------------------------------------------------------+
|  3D Villager Preview   |  Active Controls:                                        |
|                        |                                                          |
|       [ Head ]         |  [ Left Size:  50% ] [ Right Size: 50% ]                 |
|          ||            |                                                          |
|     (Left)(Right)      |  [ Button: Slider Link Mode: LINKED (Symmetric) ]        |
|          ||            |                                                          |
|       [ Torso ]        |  [ Left X-Pos:  0 ] [ Right X-Pos:  0 ]                  |
|          ||            |  [ Left Y-Pos:  0 ] [ Right Y-Pos:  0 ]                  |
|       [ Legs ]         |  [ Left Z-Pos:  0 ] [ Right Z-Pos:  0 ]                  |
|                        |  [ Button: Position Symmetry: MIRRORED ]                 |
|                        |  [ Button: Back-Face Anchor: ON (No Back-Poke) ]         |
+-----------------------------------------------------------------------------------+
```

---

## 5. 全域快速連結
* [[🌟 返回 MC 26.3 門戶|zh_tw-26.3-Home]]
* [[📐 解剖縮放與幾何學|zh_tw-26.3-Anatomical-Scaling-and-Geometry]]
* [[⚙️ 配置與遊戲規則|zh_tw-26.3-Configuration-and-GameRules]]
