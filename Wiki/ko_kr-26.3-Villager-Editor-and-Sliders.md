# 주민 편집기 및 인터랙티브 슬라이더 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 면책 조항**: 본 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최신 미출시 커밋이나 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

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

## 5. 전역 빠른 링크
* [[🌟 MC 26.3 포털로 돌아가기|ko_kr-26.3-Home]]
* [[📐 해부학적 스케일링 및 기하학|ko_kr-26.3-Anatomical-Scaling-and-Geometry]]
* [[⚙️ 구성 및 게임 규칙|ko_kr-26.3-Configuration-and-GameRules]]
