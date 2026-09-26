# 架构设计与字节码 Mixin (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库来源免责声明**：本维基文档反映**代码仓库当前的最新源码状态**，可能包含先于 CurseForge 与 Modrinth 正式构建的未发布提交或开发特性。

## 1. Official Infobox Table

| Parameter | Technical Specification |
| :--- | :--- |
| **Root Java Package** | `net.instantgratification.mcainclusive` |
| **Mixin Configuration** | `mca_inclusive_expressions_addon.mixins.json` |
| **Mixin Environment** | Pre-launch, Client, Server |
| **Refmap File** | `mca-inclusive-expressions-addon-refmap.json` |
| **Total Mixins** | 14 distinct Mixin classes |
| **Duck Interfaces** | 4 custom duck interfaces in `.ducks` package |

---

## 2. Subsystem Package Layout

```
net.instantgratification.mcainclusive/
├── MCAInclusiveExpressionsAddon.java       // Main entrypoint, registry hooks, math sampling
├── ModVersionGuard.java                    // Safety classloader validator
├── client/
│   ├── ConfigScreenHelper.java             // YACL v3 config screen builder
│   └── ModMenuIntegration.java             // ModMenu API client entrypoint
├── ducks/
│   ├── CommonVillagerModelDuck.java        // Transformation getters/setters for models
│   ├── ExtendedSliderWidgetDuck.java       // Integer setter hook for sliders
│   ├── GeneticsDuck.java                   // Genetics scale, position & Euler fields
│   └── VillagerRenderStateDuck.java        // Render state parameter bridging
├── mixin/
│   ├── CommonVillagerInterfaceMixin.java   // Mixin to CommonVillagerModel (render & copy)
│   ├── CommonVillagerModelMixin.java       // Mixin to VillagerEntityBaseModelMCA
│   ├── ExtendedSliderWidgetMixin.java      // Mixin to ExtendedSliderWidget
│   ├── GeneticsMixin.java                  // Mixin to Genetics (NBT & sampling)
│   ├── ModelPartAccessor.java              // Accessor to ModelPart (cubes & children)
│   ├── PlayerArmorExtendedModelMixin.java  // Mixin to PlayerArmorExtendedModel
│   ├── PlayerEntityExtendedModelMixin.java // Mixin to PlayerEntityExtendedModel
│   ├── TraitsMixin.java                    // Mixin to Traits (randomize hook)
│   ├── VillagerDimensionsMixin.java        // Mixin to VillagerDimensions.Mutable
│   ├── VillagerEditorScreenAccess.java     // Accessor for editor villager instances
│   ├── VillagerEditorScreenMixin.java      // Mixin to VillagerEditorScreen (tabs & sliders)
│   ├── VillagerEditorSyncRequestMixin.java // Mixin to VillagerEditorSyncRequest (key whitelist)
│   ├── VillagerEntityMCAMixin.java         // Mixin to VillagerEntityMCA (save/load NBT)
│   ├── VillagerRenderStateMixin.java       // Mixin to VillagerRenderState
│   ├── VillagerVisualsMixin.java           // Mixin to EntityRenderer (state extraction)
│   └── VillagerVisualsRecordMixin.java     // Mixin to VillagerVisuals
└── render/
    └── TorsoClippingVertexConsumer.java    // VertexConsumer anti-back-poke clipping wrapper
```

---

## 3. Dataflow & Duck Architecture (ASCII Diagram)

```
[ Villager Entity (NBT: mca_inclusive_expressions) ]
                    |
                    v
          [ GeneticsDuck ] <---+ (VillagerEditorSyncRequest C2S Packet)
                    |          |
                    v          |
     [ EntityRenderer.extractRenderState ]
                    |
                    v
       [ VillagerRenderStateDuck ]
                    |
                    v
    [ CommonVillagerModel.setupAnim ]
                    |
                    v
       [ CommonVillagerModelDuck ]
                    |
                    v
     [ CommonVillagerInterfaceMixin.renderCommon ]
                    |
                    v
  [ TorsoClippingVertexConsumer (Clamped Output) ]
```

---

## 4. 全局快速链接
* [[🌟 返回 MC 26.3 门户|zh_cn-26.3-Home]]
* [[📐 解剖缩放与几何学|zh_cn-26.3-Anatomical-Scaling-and-Geometry]]
* [[🎛️ 村民编辑器与交互滑块|zh_cn-26.3-Villager-Editor-and-Sliders]]
