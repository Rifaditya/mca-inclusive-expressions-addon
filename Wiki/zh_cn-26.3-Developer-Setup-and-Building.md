# 开发者环境配置与构建 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库来源免责声明**：本维基文档反映**代码仓库当前的最新源码状态**，可能包含先于 CurseForge 与 Modrinth 正式构建的未发布提交或开发特性。

## 1. Technical Toolchain Specifications

| Toolchain Property | Anchor Specification |
| :--- | :--- |
| **Minecraft Version** | `MC 26.3` |
| **Mod SemVer** | `v4.5.1+26.3` |
| **Java SDK Platform** | OpenJDK 25 (Hotspot 64-bit) |
| **Gradle Wrapper** | Gradle 9.3+ (`gradlew`) |
| **Fabric Loader** | `0.19.3` |
| **Fabric API** | `0.156.1+26.3` |
| **MCA Reborn Dependency** | `8.1.4+26.3` |
| **DasikLibrary** | `>=1.8.36` |

---

## 2. Compilation & Verification Commands

```bash
./gradlew build --no-daemon
```

The compiled binary will be located at:
`build/libs/mca-inclusive-expressions-addon-4.5.1+26.3.jar`

---

## 3. 全局快速链接
* [[🌟 返回 MC 26.3 门户|zh_cn-26.3-Home]]
* [[🛠️ 开发者环境配置与构建指南|zh_cn-Developer-Setup-and-Building]]
* [[📋 全局版本兼容性矩阵|zh_cn-Version-Compatibility]]
