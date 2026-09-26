# Developer Setup & Building Guide

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

This guide outlines the development environment, toolchain specifications, and build steps required to compile and contribute to MCA Inclusive Expressions Addon across all supported versions.

---

## 🛠️ Toolchain Specifications

* **Java Development Kit (JDK)**: JDK 25 (Java 25 toolchain configured via `org.gradle.java.home=E:/JDK25`).
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

Clone the repository and submodules using Git:

```bash
git clone https://github.com/Rifaditya/mca-inclusive-expressions-addon.git
cd mca-inclusive-expressions-addon
```

Open the project directory in your preferred IDE (**IntelliJ IDEA** recommended, with the Minecraft Development plugin installed).

---

## 🔨 Build Commands

To build the mod JAR without launching a background daemon:

```bash
./gradlew build --no-daemon
```

### Automated Build Pipeline & Artifact Handling
When `./gradlew build` runs, Gradle performs the following automated steps:
1. `compileJava`: Compiles Java 25 source files with `-release 25`.
2. `processResources`: Expands mod version, loader version, and dependency bounds into `fabric.mod.json`.
3. `remapJar`: Remaps obfuscated intermediate bytecode to production environment names.
4. `archiveReleaseJar`: Custom post-evaluation task that copies the compiled JAR to:
   - Mod Local Archive: `../../Archive Jar of all versions/MC <version>/`
   - Modrinth launcher test profile: `profiles/Fabric 26.2 (1)/mods/`
   - Central Release Hub: `minecraft-mod-release-hub/archives/`

---

## 🧪 Headless Verification & Testing

To test mod compilation, run:

```bash
./gradlew check --no-daemon
```

Ensure all mixin annotations adhere to remap flags:
- Mojang-mapped classes (`EntityRenderer`, `ModelPart`, `GameRule`) use `remap = true` (default).
- MCA Reborn classes (`CommonVillagerModel`, `Genetics`, `VillagerEditorScreen`) use `remap = false`.

---

## 🔗 Related Pages
* [[Global Home Portal|Home]]
* [[MC 26.2 Developer Guide|26.2-Developer-Setup-and-Building]]
* [[MC 26.3 Developer Guide|26.3-Developer-Setup-and-Building]]
