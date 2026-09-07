# MCA Inclusive Expressions Addon - Minecraft 26.2 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼倉庫來源免責聲明**：本維基文件反映**程式碼倉庫當前的最新原始碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

歡迎查閱 **MCA Inclusive Expressions Addon v4.5.1+26.2** 面向 **Minecraft 26.2** 的專屬技術文件門戶。該版本樹包含針對此版本編譯的全部遊戲特性、3D 渲染管線、編輯器螢幕互動以及位元組碼鉤子的詳盡技術規範。

---

## 🧭 Minecraft 26.2 導航矩陣

| 功能領域 | 核心機制與聚焦點 | 專用頁面連結 |
| :--- | :--- | :--- |
| **📐 解剖縮放與幾何學** | 高斯常態分佈取樣、±1% 不對稱微調、6 軸正交平移、3 軸歐拉旋轉、TorsoClippingVertexConsumer 防穿模裁剪 | [[26.2 解剖縮放與幾何學|zh_tw-26.2-Anatomical-Scaling-and-Geometry]] |
| **🎛️ 村民編輯器與互動滑桿** | 第 5 個專屬【胸部】子標籤、3 級巢狀子分類（大小、位置、旋轉）、滑桿對稱連動、即時 3D 預覽 | [[26.2 村民編輯器與互動滑桿|zh_tw-26.2-Villager-Editor-and-Sliders]] |
| **⚙️ 配置與遊戲規則** | 伺服端權威 GameRule（force_all_breasted, full_chested_trait_chance）、YACL v3 配置介面、Ko-fi 創作者支援 | [[26.2 配置與遊戲規則|zh_tw-26.2-Configuration-and-GameRules]] |
| **🏛️ 架構設計與位元組碼 Mixin** | 套件結構設計、Duck 介面模式（GeneticsDuck 等）、14 個 SpongePowered Mixin 注入點、NBT 序列化架構 | [[26.2 架構設計與位元組碼 Mixin|zh_tw-26.2-Architecture-and-Mixins]] |
| **🔨 開發者環境配置與構建** | JDK 25 工具鏈、Fabric Loom 1.15-SNAPSHOT、Gradle 9.3+ 構建、自動多版本封存 | [[26.2 開發者環境配置與構建|zh_tw-26.2-Developer-Setup-and-Building]] |

---

## 📋 Minecraft 26.2 目標規範

- **Minecraft Anchor**: `26.2`
- **Addon Version**: `v4.5.1+26.2`
- **Fabric Loader**: Tested with `0.19.1` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.150.1+26.2`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.2`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 全域快速連結
* [[🏠 返回全域首頁門戶|zh_tw-Home]]
* [[📋 全域版本相容性矩陣|zh_tw-Version-Compatibility]]
* [[🔧 疑難排解與常見問題|zh_tw-Troubleshooting-and-FAQ]]
