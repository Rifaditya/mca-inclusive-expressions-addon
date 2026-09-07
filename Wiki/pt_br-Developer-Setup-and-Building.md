# Guia de Configuração de Desenvolvedor e Compilação

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Isenção de responsabilidade da fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos em desenvolvimento à frente dos lançamentos públicos no CurseForge e Modrinth.

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

## 🔗 Links Rápidos Globais
* [[🏠 Voltar ao Portal Geral|pt_br-Home]]
* [[🔨 26.2 Ambiente de Desenvolvimento e Toolchain|pt_br-26.2-Developer-Setup-and-Building]]
* [[🔨 26.3 Ambiente de Desenvolvimento e Toolchain|pt_br-26.3-Developer-Setup-and-Building]]
