# 配置與遊戲規則 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼倉庫來源免責聲明**：本維基文件反映**程式碼倉庫當前的最新原始碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

## 1. Official Infobox Table

| Parameter | Technical Specification |
| :--- | :--- |
| **GameRule Category ID** | `mca_inclusive_expressions_addon:category` |
| **GameRule Category Title** | `§l▼ MCA Inclusive Expressions` |
| **Configuration Library** | YetAnotherConfigLib (YACL v3) + ModMenu |
| **Permission Level** | Level 2 (Server Operator) for `/gamerule` commands |
| **Anti-Nanny Standard** | True Sandbox Freedom: No arbitrary upper caps |

---

## 2. In-Game Player & Administrator Workflow

```bash
# Force 3D breasts on all villagers globally
/gamerule mca_inclusive_expressions:force_all_breasted true

# Query the current status
/gamerule mca_inclusive_expressions:force_all_breasted

# Set the natural spawn chance of Full-Chested trait to 20%
/gamerule mca_inclusive_expressions:full_chested_trait_chance 20

# Query the current chance
/gamerule mca_inclusive_expressions:full_chested_trait_chance
```

---

## 3. Probability Math & Sampling Equations

$$P(\text{Full-Chested}) = \frac{C_{\text{trait}}}{100} \quad \text{where } C_{\text{trait}} \in [0, 100]$$

---

## 4. Server-Authoritative GameRule Sync Flowchart

```
          [ Server World Init ]
                    |
                    v
    [ Register GameRule Category: "§l▼ MCA Inclusive Expressions" ]
                    |
      +-------------+-------------+
      |                           |
      v                           v
[ "force_all_breasted" ]  [ "full_chested_trait_chance" ]
(Boolean, Default: false) (Integer, Default: 5, Range: 0-100)
      |                           |
      +-------------+-------------+
                    |
                    v
       [ Villager Entity Spawn Tick ]
                    |
      +-------------+-------------+
      | Is Male & chance met?     |
      | OR force_all_breasted?    |
      +-------------+-------------+
             /             \
       (Yes)/               \(No)
           v                 v
   [ Assign Trait ]    [ Standard Male ]
   [ Base Scale: 1.0 ] [ Base Scale: 0.0 ]
```

---

## 5. 全域快速連結
* [[🌟 返回 MC 26.3 門戶|zh_tw-26.3-Home]]
* [[📐 解剖縮放與幾何學|zh_tw-26.3-Anatomical-Scaling-and-Geometry]]
* [[🏛️ 架構設計與位元組碼 Mixin|zh_tw-26.3-Architecture-and-Mixins]]
