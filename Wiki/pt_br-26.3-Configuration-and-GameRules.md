# Configuração e GameRules (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Isenção de responsabilidade da fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos em desenvolvimento à frente dos lançamentos públicos no CurseForge e Modrinth.

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

## 5. Links Rápidos Globais
* [[🌟 Voltar ao Portal MC 26.3|pt_br-26.3-Home]]
* [[📐 Escala Anatômica e Geometria|pt_br-26.3-Anatomical-Scaling-and-Geometry]]
* [[🏛️ Arquitetura e Mixins de Bytecode|pt_br-26.3-Architecture-and-Mixins]]
