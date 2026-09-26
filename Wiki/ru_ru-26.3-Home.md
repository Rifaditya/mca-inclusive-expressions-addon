# MCA Inclusive Expressions Addon - Minecraft 26.3 Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Отказ от ответственности за источник репозитория**: Документация в этой вики отражает **текущее состояние исходного кода в репозитории**, которое может включать недавние невыпущенные коммиты или разрабатываемые функции, опережающие общедоступные сборки на CurseForge и Modrinth.

Добро пожаловать на технический портал документации **MCA Inclusive Expressions Addon v4.5.1+26.3** для **Minecraft 26.3**. В этом изолированном дереве версий собраны исчерпывающие технические данные о функциях, 3D рендере, интерфейсе редактора и хуках байт-кода.

---

## 🧭 Матрица навигации Minecraft 26.3

| Область функций | Ключевые механизмы и фокус | Ссылка на страницу |
| :--- | :--- | :--- |
| **📐 Анатомическое масштабирование и геометрия** | Нормальное распределение Гаусса, ±1% асимметрия, 6 осей смещения, 3 оси углов Эйлера, отсечение TorsoClippingVertexConsumer | [[26.3 Анатомическое масштабирование и геометрия|ru_ru-26.3-Anatomical-Scaling-and-Geometry]] |
| **🎛️ Редактор жителей и интерактивные ползунки** | 5-я вкладка [ Грудь ], 3 подкатегории (Размер, Позиция, Вращение), синхронизация ползунков, 3D предпросмотр | [[26.3 Редактор жителей и интерактивные ползунки|ru_ru-26.3-Villager-Editor-and-Sliders]] |
| **⚙️ Конфигурация и игровые правила** | GameRule сервера (force_all_breasted, full_chested_trait_chance), интерфейс YACL v3, поддержка Ko-fi | [[26.3 Конфигурация и игровые правила|ru_ru-26.3-Configuration-and-GameRules]] |
| **🏛️ Архитектура и миксины байт-кода** | Структура пакетов, duck-интерфейсы (GeneticsDuck), 14 SpongePowered миксинов, сериализация NBT | [[26.3 Архитектура и миксины байт-кода|ru_ru-26.3-Architecture-and-Mixins]] |
| **🔨 Среда разработчика и сборка** | Тулчейн JDK 25, Fabric Loom 1.15-SNAPSHOT, сборка Gradle 9.3+, автоархивация | [[26.3 Среда разработчика и сборка|ru_ru-26.3-Developer-Setup-and-Building]] |

---

## 📋 Спецификации для Minecraft 26.3

- **Minecraft Anchor**: `26.3-snapshot-6`
- **Addon Version**: `v4.5.1+26.3`
- **Fabric Loader**: Tested with `0.19.3` (minimum `>=0.16.0`)
- **Fabric API**: Tested with `0.156.1+26.3`
- **MCA Reborn Compatibility**: MCA Reborn Fabric `8.1.4+26.3`
- **DasikLibrary Dependency**: Bounded at `>=1.8.36` (bundled `1.8.38`)
- **Java Virtual Machine**: OpenJDK 25 (`-release 25`)

---

## 🔗 Глобальные ссылки
* [[🏠 Вернуться на главную|ru_ru-Home]]
* [[📋 Глобальная матрица совместимости|ru_ru-Version-Compatibility]]
* [[🔧 Устранение неполадок и FAQ|ru_ru-Troubleshooting-and-FAQ]]
