# 疑難排解與常見問題 (FAQ) (繁體中文)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼庫來源免責聲明**：本維基文件反映**程式碼庫當前的最新源碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

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
* [[MCA Inclusive Expressions Addon 官方維基|zh_tw-Home]]
* [[版本相容性矩陣|zh_tw-Version-Compatibility]]
* [[Global English Portal|Home]]
