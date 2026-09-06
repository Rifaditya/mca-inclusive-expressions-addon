# Version Compatibility Matrix

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

MCA Inclusive Expressions Addon is designed under strict lockstep parity principles across modern Minecraft version anchors. Each targeted version has a dedicated build target and archive JAR.

---

## 📊 Comprehensive Compatibility Matrix

| Minecraft Anchor | Addon Release | Fabric Loader | Fabric API | MCA Reborn | DasikLibrary | Java Level |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | `>=0.16.0` (tested `0.19.1`) | `0.150.1+26.2` | `8.1.4+26.2` | `>=1.8.36` (bundled `1.8.38`) | Java 25 |
| **Minecraft 26.3** | `v4.5.1+26.3` | `>=0.16.0` (tested `0.19.3`) | `0.156.1+26.3` | `8.1.4+26.3` | `>=1.8.36` (bundled `1.8.38`) | Java 25 |

---

## 🧩 Mod Dependencies & Compatibility

### 1. Hard Dependencies (Mandatory)
* **Fabric Loader**: Minimum version `>=0.16.0`. Recommended: `0.19.1+` for 26.2, `0.19.3+` for 26.3.
* **Fabric API**: Required for lifecycle hooks, resource reloads, and networking.
* **MCA Reborn (Minecraft Comes Alive)**: Hard dependency (`mca: "*"`). Provides the villager base entity, genetics engine, villager editor GUI, and base character models.
* **DasikLibrary**: Universal library dependency bounded at `>=1.8.36`. Provides branded social support helper, dynamic gamerule utilities, and math facades.

### 2. Optional Dependencies (Client Enhancements)
* **ModMenu**: Provides in-game mod list integration and access to the client configuration screen.
* **YetAnotherConfigLib (YACL v3)**: Recommended client configuration library (`yet_another_config_lib_v3: "*"`). When installed alongside ModMenu, enables the in-game options screen with real-time sliders and Ko-fi creator support buttons. If absent, the mod operates seamlessly using server GameRules.

---

## 🏛️ 1 Jar 1 Version Policy vs. Universal Library Bounds

- **MCA Inclusive Expressions Addon**: Adheres to the strict **1 Jar 1 Version Policy**. Because MCA Reborn and Minecraft internal rendering APIs undergo bytecode and classloader changes between versions, dedicated JARs are compiled for `26.2` (`mca-inclusive-expressions-addon-4.5.1+26.2.jar`) and `26.3` (`mca-inclusive-expressions-addon-4.5.1+26.3.jar`).
- **DasikLibrary**: Operates as an open-bounded universal library (`>=26.1.2-`), providing binary compatibility across all 26.x iterations.

---

## 🔗 Quick Links
* [[Return to Global Home|Home]]
* [[MC 26.2 Documentation Portal|26.2-Home]]
* [[MC 26.3 Documentation Portal|26.3-Home]]
