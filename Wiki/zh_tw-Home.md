# MCA Inclusive Expressions Addon 官方維基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **程式碼庫來源免責聲明**：本維基文件反映**程式碼庫當前的最新源碼狀態**，可能包含先於 CurseForge 與 Modrinth 正式構建的未發布提交或開發特性。

歡迎查閱 **MCA Inclusive Expressions** 官方技術文件與玩家指南。本模組為 Minecraft Comes Alive (MCA Reborn) 提供獨立的胸部模型縮放、正交三維位置調整、歐拉角旋轉控制及實時村民編輯器集成。

---

## 🧭 多版本文件導航入口

| Minecraft Version | Mod Version | Dedicated Portal Link |
| :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | [[👉 Minecraft 26.2|zh_tw-26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | [[👉 Minecraft 26.3|zh_tw-26.3-Home]] |

---

## 🌟 核心玩法與技術特性

- **獨立雙側 3D MatrixStack 縮放**：左側與右側胸部模型可獨立在 0.0x 至 4.44x 之間調節，並支援高斯常態分佈隨機生成與自然不對稱微調（±1%）。
- **正交 6 軸位置調節**：擺脫 MCA 原生 -35° 傾角影響，實現真正正交三維平移（上即是上，左即是左，前即是前）。
- **3 軸歐拉旋轉控制**：俯仰（Pitch）、偏航（Yaw）與翻滾（Roll）全向微調。
- **TorsoClippingVertexConsumer 軀幹防穿模引擎**：透過矩陣求逆運算將頂點限制在軀幹背壁以內，杜絕大尺寸模型背部穿模。
- **村民編輯器 GUI 擴展**：在村民編輯器中增加第 5 個專屬子頁面標籤【胸部】，包含大小、位置與旋轉 3 個二級分類。
- **動態特質與遊戲規則**：動態注入 `full_chested` 特質，並提供伺服端權限遊戲規則 `force_all_breasted` 與 `full_chested_trait_chance`。

---

## 📚 版本相容性矩陣 & FAQ
* [[版本相容性矩陣|zh_tw-Version-Compatibility]]
* [[疑難排解與常見問題 (FAQ)|zh_tw-Troubleshooting-and-FAQ]]
* [[Global English Portal|Home]]

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
