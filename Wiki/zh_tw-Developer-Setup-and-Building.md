# 開發者環境配置與構建指南

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼倉庫來源免責聲明**：本維基文件反映**程式碼倉庫當前的最新原始碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

## 🛠️ Toolchain Specifications

* **Java Development Kit (JDK)**: JDK 25 (`org.gradle.java.home=E:/JDK25`).
* **Gradle**: Gradle 9.3+ with Loom `1.15-SNAPSHOT`.
* **Fabric Loader**: Minimum `0.16.0`, tested with `0.19.1` (MC 26.2) and `0.19.3` (MC 26.3).
* **Mappings**: Official Mojang mappings with Parchment layer support.
* **Loom Configuration**:
  ```groovy
  loom {
      mixin {
          defaultRefmapName = "mca-inclusive-expressions-addon-refmap.json"
      }
  }
  ```

---

## 📥 Cloning & Workspace Preparation

```bash
git clone https://github.com/Rifaditya/mca-inclusive-expressions-addon.git
cd mca-inclusive-expressions-addon
```

---

## 🔨 Build Commands

```bash
./gradlew build --no-daemon
```

---

## 🧪 Headless Verification & Testing

```bash
./gradlew check --no-daemon
```

---

## 🔗 全域快速連結
* [[🏠 返回全域首頁門戶|zh_tw-Home]]
* [[🔨 26.2 開發者配置與構建|zh_tw-26.2-Developer-Setup-and-Building]]
* [[🔨 26.3 開發者配置與構建|zh_tw-26.3-Developer-Setup-and-Building]]
