# MCA Inclusive Expressions Addon - Minecraft 26.2 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 면책 조항**: 본 위키의 문서는 CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 최신 미출시 커밋이나 개발 기능을 포함할 수 있는 **저장소의 현재 소스 코드 상태**를 반영합니다.

**Minecraft 26.2**용 **MCA Inclusive Expressions Addon v4.5.1+26.2** 공식 기술 문서 포털에 오신 것을 환영합니다. 3D 렌더링 파이프라인, 편집기 GUI, 바이트코드 훅에 대한 상세 사양을 제공합니다.

---

## 🧭 Minecraft 26.2 내비게이션 매트릭스

| 기능 영역 | 핵심 메커니즘 및 주요 내용 | 전용 페이지 링크 |
| :--- | :--- | :--- |
| **📐 해부학적 스케일링 및 기하학** | 가우스 정규 분포, ±1% 비대칭 미세 조정, 6축 직교 이동, 3축 오일러 회전, TorsoClippingVertexConsumer 관통 방지 | [[26.2 해부학적 스케일링 및 기하학|ko_kr-26.2-Anatomical-Scaling-and-Geometry]] |
| **🎛️ 주민 편집기 및 인터랙티브 슬라이더** | 전용 5번째 [ 가슴 ] 하위 탭, 3단계 중첩 카테고리(크기, 위치, 회전), 슬라이더 대칭 연동, 실시간 3D 미리보기 | [[26.2 주민 편집기 및 인터랙티브 슬라이더|ko_kr-26.2-Villager-Editor-and-Sliders]] |
| **⚙️ 구성 및 게임 규칙** | 서버 권한 GameRules, YACL v3 설정 화면, Ko-fi 후원 연동 | [[26.2 구성 및 게임 규칙|ko_kr-26.2-Configuration-and-GameRules]] |
| **🏛️ 아키텍처 및 바이트코드 Mixin** | 패키지 레이아웃, Duck 인터페이스 패턴, 14개 SpongePowered Mixin 주입점, NBT 직렬화 | [[26.2 아키텍처 및 바이트코드 Mixin|ko_kr-26.2-Architecture-and-Mixins]] |
| **🔨 개발자 설정 및 툴체인** | JDK 25 툴체인, Fabric Loom 1.15-SNAPSHOT, Gradle 9.3+ 빌드 | [[26.2 개발자 설정 및 툴체인|ko_kr-26.2-Developer-Setup-and-Building]] |

---

## 📋 Minecraft 26.2 대상 사양

- **Minecraft Anchor**: `26.2`
- **Addon Version**: `v4.5.1+26.2`
- **Fabric Loader**: Tested with `0.19.1` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.150.1+26.2`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.2`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 전역 빠른 링크
* [[🏠 전역 홈 포털로 돌아가기|ko_kr-Home]]
* [[📋 전역 버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[🔧 문제 해결 및 FAQ|ko_kr-Troubleshooting-and-FAQ]]
