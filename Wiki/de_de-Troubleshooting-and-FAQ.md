# Fehlerbehebung & Häufig Gestellte Fragen (FAQ) (Deutsch)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository-Quelltext-Hinweis**: Diese Dokumentation spiegelt den **aktuellen Quellcode-Stand im Repository** wider, einschließlich unvollendeter Commits vor offiziellen Veröffentlichungen auf CurseForge und Modrinth.

本セクションでは、MCA Inclusive Expressions Addon に関する主要な技術的質問とトラブルシューティング手順を解説します。

---

## 🛠️ FAQ & Troubleshooting Summary

1. **Armor Clipping (防具・背中貫通問題)**:
   - 村人エディタで **Back-Face Anchor: ON** を有効にすることで、`TorsoClippingVertexConsumer` が頂点を胸壁面（$Z \le 1.5/16.0$）に自動固定します。
2. **GameRules (サーバー権限)**:
   - `/gamerule mca_inclusive_expressions:force_all_breasted true`
   - `/gamerule mca_inclusive_expressions:full_chested_trait_chance 25`
3. **Genetics & NBT**:
   - データは独立タグ `mca_inclusive_expressions` に保存され、MCA Reborn 本体の `MCAData` を上書きすることなく安全に永続化されます。

---

## 🔗 Quick Links
* [[Offizielles MCA Inclusive Expressions Addon Wiki|de_de-Home]]
* [[Versions-Kompatibilitätsmatrix|de_de-Version-Compatibility]]
* [[Global English Portal|Home]]
