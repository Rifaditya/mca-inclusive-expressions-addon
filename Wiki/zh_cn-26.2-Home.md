# MCA Inclusive Expressions Addon - Minecraft 26.2 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库来源免责声明**：本维基文档反映**代码仓库当前的最新源码状态**，可能包含先于 CurseForge 与 Modrinth 正式构建的未发布提交或开发特性。

欢迎查阅 **MCA Inclusive Expressions Addon v4.5.1+26.2** 面向 **Minecraft 26.2** 的专属技术文档门户。该版本树包含针对此版本编译的全部游戏特性、3D 渲染管线、编辑器屏幕交互以及字节码钩子的详尽技术规范。

---

## 🧭 Minecraft 26.2 导航矩阵

| 功能领域 | 核心机制与聚焦点 | 专用页面链接 |
| :--- | :--- | :--- |
| **📐 解剖缩放与几何学** | 高斯正态分布采样、±1% 不对称微调、6 轴正交平移、3 轴欧拉旋转、TorsoClippingVertexConsumer 防穿模裁剪 | [[26.2 解剖缩放与几何学|zh_cn-26.2-Anatomical-Scaling-and-Geometry]] |
| **🎛️ 村民编辑器与交互滑块** | 第 5 个专属【胸部】子标签、3 级嵌套子分类（大小、位置、旋转）、滑块对称联动、实时 3D 预览 | [[26.2 村民编辑器与交互滑块|zh_cn-26.2-Villager-Editor-and-Sliders]] |
| **⚙️ 配置与游戏规则** | 服务端权威 GameRule（force_all_breasted, full_chested_trait_chance）、YACL v3 配置界面、Ko-fi 创作者支持 | [[26.2 配置与游戏规则|zh_cn-26.2-Configuration-and-GameRules]] |
| **🏛️ 架构设计与字节码 Mixin** | 包结构设计、Duck 接口模式（GeneticsDuck 等）、14 个 SpongePowered Mixin 注入点、NBT 序列化架构 | [[26.2 架构设计与字节码 Mixin|zh_cn-26.2-Architecture-and-Mixins]] |
| **🔨 开发者环境配置与构建** | JDK 25 工具链、Fabric Loom 1.15-SNAPSHOT、Gradle 9.3+ 构建、自动多版本归档 | [[26.2 开发者环境配置与构建|zh_cn-26.2-Developer-Setup-and-Building]] |

---

## 📋 Minecraft 26.2 目标规范

- **Minecraft Anchor**: `26.2`
- **Addon Version**: `v4.5.1+26.2`
- **Fabric Loader**: Tested with `0.19.1` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.150.1+26.2`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.2`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 全局快速链接
* [[🏠 返回全局主页门户|zh_cn-Home]]
* [[📋 全局版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[🔧 故障排除与常见问题|zh_cn-Troubleshooting-and-FAQ]]
