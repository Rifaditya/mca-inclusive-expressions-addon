# 개발자 설정 및 툴체인 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 면책 조항**: 본 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최신 미출시 커밋이나 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

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

## 3. 전역 빠른 링크
* [[🌟 MC 26.3 포털로 돌아가기|ko_kr-26.3-Home]]
* [[🛠️ 개발자 환경 설정 및 통합 빌드 가이드|ko_kr-Developer-Setup-and-Building]]
* [[📋 전역 버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
