# MCA Inclusive Expressions Addon 官方维基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库来源免责声明**：本维基文档反映**代码仓库当前的最新源码状态**，可能包含先于 CurseForge 与 Modrinth 正式构建的未发布提交或开发特性。

欢迎查阅 **MCA Inclusive Expressions** 官方技术文档与玩家指南。本模组为 Minecraft Comes Alive (MCA Reborn) 提供独立的胸部模型缩放、正交三维位置调整、欧拉角旋转控制及实时村民编辑器集成。

---

## 🧭 多版本文档导航入口

| Minecraft Version | Mod Version | Dedicated Portal Link |
| :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | [[👉 26.2 Documentation|26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | [[👉 26.3 Documentation|26.3-Home]] |

---

## 🌟 核心玩法与技术特性

- **独立双侧 3D MatrixStack 缩放**：左侧与右侧胸部模型可独立在 0.0x 至 4.44x 之间调节，并支持高斯正态分布随机生成与自然不对称微调（±1%）。
- **正交 6 轴位置调节**：摆脱 MCA 原生 -35° 倾角影响，实现纯正交三维平移（上即是上，左即是左，前即是前）。
- **3 轴欧拉旋转控制**：俯仰（Pitch）、偏航（Yaw）和翻滚（Roll）全向微调。
- **TorsoClippingVertexConsumer 躯干防穿模引擎**：通过矩阵求逆运算将顶点限制在躯干背壁以内，杜绝大尺寸模型背部穿模。
- **村民编辑器 GUI 扩展**：在村民编辑器中增加第 5 个专属子页面标签【胸部】，包含大小、位置与旋转 3 个二级分类。
- **动态特质与游戏规则**：动态注入 `full_chested` 特质，并提供服务端权限游戏规则 `force_all_breasted` 与 `full_chested_trait_chance`。

---

## 📚 版本兼容性矩阵 & FAQ
* [[版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[故障排查与常见问题 (FAQ)|zh_cn-Troubleshooting-and-FAQ]]
* [[Global English Portal|Home]]

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
