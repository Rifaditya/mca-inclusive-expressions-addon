# Guide de Configuration Développeur & Compilation

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Avertissement sur la source du dépôt** : La documentation de ce wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure des commits non publiés ou des fonctionnalités en cours de développement en avance sur CurseForge et Modrinth.

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

## 🔗 Liens Globaux
* [[🏠 Retour au Portail Général|fr_fr-Home]]
* [[🔨 26.2 Environnement Développeur & Outils|fr_fr-26.2-Developer-Setup-and-Building]]
* [[🔨 26.3 Environnement Développeur & Outils|fr_fr-26.3-Developer-Setup-and-Building]]
