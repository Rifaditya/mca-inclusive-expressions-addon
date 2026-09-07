# Pengaturan Pengembang & Toolchain (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi dalam Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis atau fitur dalam tahap pengembangan mendahului build rilis publik di CurseForge dan Modrinth.

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

## 3. Tautan Cepat Global
* [[🌟 Kembali ke Portal MC 26.2|id_id-26.2-Home]]
* [[🛠️ Pengaturan Pengembang & Panduan Build Terpadu|id_id-Developer-Setup-and-Building]]
* [[📋 Matriks Kompatibilitas Versi Global|id_id-Version-Compatibility]]
