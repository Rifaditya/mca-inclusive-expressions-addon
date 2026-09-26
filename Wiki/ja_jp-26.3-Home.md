# MCA Inclusive Expressions Addon - Minecraft 26.3 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソース免責事項**：本 Wiki ドキュメントは**リポジトリ内の現在のソースコード状態**を反映しており、CurseForge および Modrinth での公開ビルドに先駆けた最新の未リリースコミットや開発中の機能を含む場合があります。

**MCA Inclusive Expressions Addon v4.5.1+26.3** の **Minecraft 26.3** 向け技術ドキュメントポータルへようこそ。3Dレンダリング、エディタUI、バイトコードフックの仕様を網羅しています。

---

## 🧭 Minecraft 26.3 ナビゲーションマトリクス

| 機能分野 | 重点領域とメカニズム | 専用ページリンク |
| :--- | :--- | :--- |
| **📐 解剖学的スケーリングと幾何学** | ガウス正規分布、±1%の非対称性、6軸直交移動、3軸オイラー角回転、TorsoClippingVertexConsumer 背面突き抜け防止 | [[26.3 解剖学的スケーリングと幾何学|ja_jp-26.3-Anatomical-Scaling-and-Geometry]] |
| **🎛️ 村人エディタとインタラクティブスライダー** | 第5の【胸部】サブタブ、3つの階層カテゴリ（サイズ・位置・回転）、対称リンク、リアルタイム3Dプレビュー | [[26.3 村人エディタとインタラクティブスライダー|ja_jp-26.3-Villager-Editor-and-Sliders]] |
| **⚙️ 設定とゲームルール** | サーバー権威 GameRules、YACL v3 設定画面、Ko-fi クリエイター支援 | [[26.3 設定とゲームルール|ja_jp-26.3-Configuration-and-GameRules]] |
| **🏛️ アーキテクチャ設計とバイトコード Mixin** | パッケージ設計、Duck インターフェース、14 の SpongePowered Mixin、NBT 構造 | [[26.3 アーキテクチャ設計とバイトコード Mixin|ja_jp-26.3-Architecture-and-Mixins]] |
| **🔨 開発環境セットアップとツールチェーン** | JDK 25 ツールチェーン、Fabric Loom 1.15-SNAPSHOT、Gradle 9.3+ | [[26.3 開発環境セットアップとツールチェーン|ja_jp-26.3-Developer-Setup-and-Building]] |

---

## 📋 Minecraft 26.3 ターゲット仕様

- **Minecraft Anchor**: `26.3-snapshot-6`
- **Addon Version**: `v4.5.1+26.3`
- **Fabric Loader**: Tested with `0.19.3` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.156.1+26.3`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.3`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 グローバルクイックリンク
* [[🏠 メインポータルへ戻る|ja_jp-Home]]
* [[📋 バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[🔧 トラブルシューティング & FAQ|ja_jp-Troubleshooting-and-FAQ]]
