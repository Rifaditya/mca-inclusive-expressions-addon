# Ambiente de Desenvolvimento e Toolchain (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Isenção de responsabilidade da fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos em desenvolvimento à frente dos lançamentos públicos no CurseForge e Modrinth.

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

## 3. Links Rápidos Globais
* [[🌟 Voltar ao Portal MC 26.2|pt_br-26.2-Home]]
* [[🛠️ Configuração de Desenvolvedor e Compilação|pt_br-Developer-Setup-and-Building]]
* [[📋 Matriz de Compatibilidade Global|pt_br-Version-Compatibility]]
