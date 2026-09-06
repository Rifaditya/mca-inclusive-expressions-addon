# 故障排查与常见问题 (FAQ) (简体中文)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库来源免责声明**：本维基文档反映**代码仓库当前的最新源码状态**，可能包含先于 CurseForge 与 Modrinth 正式构建的未发布提交或开发特性。

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
* [[MCA Inclusive Expressions Addon 官方维基|zh_cn-Home]]
* [[版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[Global English Portal|Home]]
