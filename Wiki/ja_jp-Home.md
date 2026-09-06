# MCA Inclusive Expressions Addon 公式ウィキ

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースに関する免責事項**: 本ウィキの内容は**リポジトリの最新ソースコード状態**を反映しており、CurseForge や Modrinth での一般公開ビルドに先駆けた最新コミットが含まれる場合があります。

**MCA Inclusive Expressions** の公式技術ドキュメントおよびプレイヤーガイドへようこそ。本アドオンは Minecraft Comes Alive (MCA Reborn) に独立した3Dモデル拡縮、直交3次元位置調整、オイラー角回転、および村人エディタGUIを提供します。

---

## 🧭 バージョン選択ポータル

| Minecraft Version | Mod Version | Dedicated Portal Link |
| :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | [[👉 26.2 Documentation|26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | [[👉 26.3 Documentation|26.3-Home]] |

---

## 🌟 主な機能とゲームプレイ仕様

- **独立した左右3D MatrixStackスケール**: ガウス分布と自然な非対称性（±1%）を備え、0.0倍から4.44倍まで個別に調整可能。
- **直交6軸位置カスタマイズ**: MCA本来の-35°チルト角に左右されない真の直交座標移動（上は上、左は左、前は前）。
- **3軸オイラー回転制御**: ピッチ（Pitch）、ヨー（Yaw）、ロール（Roll）の自由な角度調整。
- **TorsoClippingVertexConsumer 貫通防止クリッピング**: 大規模スケール時にモデルが背中を突き抜ける現象を逆行列計算で完全に防止。
- **村人エディタ統合**: 第5の専用タブ【胸部】を追加し、［サイズ］［位置］［回転］の3つのサブカテゴリを搭載。
- **サーバーGameRule制御**: `force_all_breasted` および `full_chested_trait_chance` による確実なサーバー統括。

---

## 📚 バージョン互換性マトリックス & FAQ
* [[バージョン互換性マトリックス|ja_jp-Version-Compatibility]]
* [[トラブルシューティングとよくある質問 (FAQ)|ja_jp-Troubleshooting-and-FAQ]]
* [[Global English Portal|Home]]

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
