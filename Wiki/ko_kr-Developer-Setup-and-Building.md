# 개발자 환경 설정 및 통합 빌드 가이드

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 면책 조항**: 본 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최신 미출시 커밋이나 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

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

## 🔗 전역 빠른 링크
* [[🏠 전역 홈 포털로 돌아가기|ko_kr-Home]]
* [[🔨 26.2 개발자 설정 및 툴체인|ko_kr-26.2-Developer-Setup-and-Building]]
* [[🔨 26.3 개발자 설정 및 툴체인|ko_kr-26.3-Developer-Setup-and-Building]]
