# MCA Inclusive Expressions Addon - Minecraft 26.2 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Isenção de responsabilidade da fonte do repositório**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes não lançados ou recursos em desenvolvimento à frente dos lançamentos públicos no CurseForge e Modrinth.

Bem-vindo ao portal de documentação técnica do **MCA Inclusive Expressions Addon v4.5.1+26.2** no **Minecraft 26.2**. Este guia cobre renderização 3D, editor GUI e hooks de bytecode.

---

## 🧭 Matriz de Navegação do Minecraft 26.2

| Área de Recurso | Mecânicas e Foco | Link da Página |
| :--- | :--- | :--- |
| **📐 Escala Anatômica e Geometria** | Distribuição gaussiana, assimetria de ±1%, posicionamento de 6 eixos, rotações de Euler, TorsoClippingVertexConsumer | [[26.2 Escala Anatômica e Geometria|pt_br-26.2-Anatomical-Scaling-and-Geometry]] |
| **🎛️ Editor de Aldeões e Controles Deslizantes** | 5ª aba [ Busto ], 3 subcategorias (Tamanho, Posição, Rotação), modos simétricos, visualização 3D | [[26.2 Editor de Aldeões e Controles Deslizantes|pt_br-26.2-Villager-Editor-and-Sliders]] |
| **⚙️ Configuração e GameRules** | GameRules do servidor, interface YACL v3, integração Ko-fi | [[26.2 Configuração e GameRules|pt_br-26.2-Configuration-and-GameRules]] |
| **🏛️ Arquitetura e Mixins de Bytecode** | Estrutura de pacotes, interfaces duck, 14 mixins SpongePowered, esquema NBT | [[26.2 Arquitetura e Mixins de Bytecode|pt_br-26.2-Architecture-and-Mixins]] |
| **🔨 Ambiente de Desenvolvimento e Toolchain** | Toolchain JDK 25, Loom 1.15-SNAPSHOT, Gradle 9.3+ | [[26.2 Ambiente de Desenvolvimento e Toolchain|pt_br-26.2-Developer-Setup-and-Building]] |

---

## 📋 Especificações Alvo do Minecraft 26.2

- **Minecraft Anchor**: `26.2`
- **Addon Version**: `v4.5.1+26.2`
- **Fabric Loader**: Tested with `0.19.1` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.150.1+26.2`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.2`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 Links Rápidos Globais
* [[🏠 Voltar ao Portal Geral|pt_br-Home]]
* [[📋 Matriz de Compatibilidade Global|pt_br-Version-Compatibility]]
* [[🔧 Solução de Problemas e FAQ|pt_br-Troubleshooting-and-FAQ]]
