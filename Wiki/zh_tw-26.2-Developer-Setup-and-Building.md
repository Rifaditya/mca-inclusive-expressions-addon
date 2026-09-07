# 開發者環境配置與構建 (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼倉庫來源免責聲明**：本維基文件反映**程式碼倉庫當前的最新原始碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

## 1. Technical Toolchain Specifications

| Toolchain Property | Anchor Specification |
| :--- | :--- |
| **Minecraft Version** | `MC 26.2` |
| **Mod SemVer** | `v4.5.1+26.2` |
| **Java SDK Platform** | OpenJDK 25 (Hotspot 64-bit) |
| **Gradle Wrapper** | Gradle 9.3+ (`gradlew`) |
| **Fabric Loader** | `0.19.1` |
| **Fabric API** | `0.150.1+26.2` |
| **MCA Reborn Dependency** | `8.1.4+26.2` |
| **DasikLibrary** | `>=1.8.36` |

---

## 2. Compilation & Verification Commands

```bash
./gradlew build --no-daemon
```

The compiled binary will be located at:
`build/libs/mca-inclusive-expressions-addon-4.5.1+26.2.jar`

---

## 3. 全域快速連結
* [[🌟 返回 MC 26.2 門戶|zh_tw-26.2-Home]]
* [[🛠️ 開發者環境配置與構建指南|zh_tw-Developer-Setup-and-Building]]
* [[📋 全域版本相容性矩陣|zh_tw-Version-Compatibility]]
