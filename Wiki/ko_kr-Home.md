# MCA Inclusive Expressions Addon 공식 위키

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 안내문**: 본 위키의 문서는 CurseForge 및 Modrinth 공개 배포 빌드 이전의 최신 커밋을 포함할 수 있는 **저장소의 최신 소스 코드 상태**를 반영합니다.

**MCA Inclusive Expressions** 공식 기술 문서 및 가이드에 오신 것을 환영합니다. 본 모드는 Minecraft Comes Alive (MCA Reborn)를 위한 독립적인 3D 모델 크기 조절, 직교 3차원 위치 조정, 오일러 회전 각도 제어 및 주민 에디터 확장 기능을 제공합니다.

---

## 🧭 버전 선택 내비게이션 포털

| Minecraft Version | Mod Version | Dedicated Portal Link |
| :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | [[👉 26.2 Documentation|26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | [[👉 26.3 Documentation|26.3-Home]] |

---

## 🌟 주요 기능 및 게임플레이 사양

- **독립적인 좌우 3D MatrixStack 스케일링**: 0.0x부터 4.44x까지 독립 조절 가능하며, 가우스 정규분포 및 자연스러운 비대칭 편차(±1%) 지원.
- **직교 6축 위치 커스터마이징**: MCA 고유의 -35° 기울기에 구애받지 않는 진정한 직교 3D 이동 (위는 위, 좌는 좌, 앞은 앞).
- **3축 오일러 회전 제어**: 피치(Pitch), 요(Yaw), 롤(Roll) 각도의 정밀한 회전 조정.
- **TorsoClippingVertexConsumer 몸통 관통 방지 엔진**: 대형 스케일 시 등 뒤로 모델이 관통하는 현상을 역행렬 변환을 통해 방지.
- **주민 에디터 GUI 통합**: 캐릭터 탭 내 5번째 전용 하위 탭 [ 가슴 ] 추가 및 [ 크기 ], [ 위치 ], [ 회전 ] 서브 카테고리 제공.
- **서버 GameRule 통제**: `force_all_breasted` 및 `full_chested_trait_chance` 규칙을 통한 전역 서버 제어.

---

## 📚 버전 호환성 매트릭스 & FAQ
* [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[문제 해결 및 자주 묻는 질문 (FAQ)|ko_kr-Troubleshooting-and-FAQ]]
* [[Global English Portal|Home]]

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
