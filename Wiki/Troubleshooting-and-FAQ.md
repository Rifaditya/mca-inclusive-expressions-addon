# Troubleshooting & Frequently Asked Questions (FAQ)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

This guide provides in-depth technical solutions, diagnostic explanations, and troubleshooting workflows for MCA Inclusive Expressions Addon.

---

## 🛠️ Common Technical Issues & Resolutions

### 1. Armor Clipping & Back-Poking into the Body
* **Symptom**: When chest scale is increased above `1.5x`, the rear faces of the breast cubes poke through the back of the villager's torso, or armor chestplates clip into the chest volume.
* **Root Cause**: Minecraft's standard cube rendering expands cubes symmetrically outwards in all directions from the local box origin. Without clipping guards, growth in $+Z$ expands deeper into the torso.
* **Solution**:
  1. Open the in-game Villager Editor GUI (**[ Character ]** $\to$ **[ Breast ]** $\to$ **[ Position ]**).
  2. Toggle the button **Back-Face Anchor: ON (No Back-Poke)**.
  3. When enabled, `TorsoClippingVertexConsumer` dynamically intercepts all vertex emit operations, transforms vertices into torso-local coordinate space via inverse matrix multiplication, and clamps all vertices exceeding $Z = 1.5 / 16.0\text{f}$ directly flush against the torso back wall.
  4. For armor clipping, ensure your armor textures follow MCA Reborn's standard 3D layer expansion (`PlayerArmorExtendedModelMixin` automatically scales armor cubes by $S_{\text{armor}} = S \times 0.55 + 0.20$).

---

### 2. Villager Trait Inheritance & Male Villagers
* **Symptom**: Male villagers spawn without breasts, or offspring do not inherit customized breast dimensions.
* **Root Cause**: By default, MCA Reborn restricts breast generation to female villagers. MCA Inclusive Expressions registers a dynamic trait `full_chested` with a default 5% spawn chance for male villagers.
* **Solution**:
  - To grant breasts to all male villagers globally, enable the server-authoritative GameRule:
    ```bash
    /gamerule mca_inclusive_expressions:force_all_breasted true
    ```
  - To adjust the natural spawn probability of the `full_chested` trait (0% to 100%), run:
    ```bash
    /gamerule mca_inclusive_expressions:full_chested_trait_chance 25
    ```
  - In the Villager Editor GUI, navigate to the **[ Traits ]** tab and click on the green `Full-Chested` trait button to manually toggle the trait.

---

### 3. Editor Sliders Resetting on World Reload
* **Symptom**: Custom slider settings appear to revert when re-entering a world or restarting a dedicated server.
* **Root Cause**: Older addons inadvertently write data into MCA's internal `MCAData` subtag, which MCA Reborn overwrites during entity serialization.
* **Solution**:
  - MCA Inclusive Expressions Addon isolates all custom data in a dedicated root NBT compound named `mca_inclusive_expressions`.
  - In `VillagerEntityMCAMixin`, both `addAdditionalSaveData` and `readAdditionalSaveData` serialize independently through Mojang Codecs:
    ```java
    output.store("mca_inclusive_expressions", CompoundTag.CODEC, extraTag);
    ```
  - Furthermore, `VillagerEditorSyncRequestMixin` whitelists all packet keys matching `mca_inclusive_expressions:*`, ensuring client-to-server C2S sync packets are never rejected by server validation.

---

### 4. Server-Side GameRules Authority
* **Symptom**: Client configuration settings differ from multiplayer server behavior.
* **Root Cause**: GameRules are strictly server-authoritative.
* **Solution**:
  - On dedicated servers, only server operators (permission level 2+) can adjust `mca_inclusive_expressions:force_all_breasted` and `mca_inclusive_expressions:full_chested_trait_chance`.
  - Client YACL configuration sliders control local defaults and client GUI preview presets, but server GameRules dictate in-world entity generation and trait application.

---

## ❓ Frequently Asked Questions (FAQ)

#### Q1: Is this addon client-side or server-side?
**A**: MCA Inclusive Expressions is a universal mod (`"environment": "*"`). It must be installed on the **server** for GameRules, genetics persistence, and network synchronization to function, and on the **client** for 3D model rendering, vertex clipping, and Villager Editor GUI tabs.

#### Q2: Does this addon work with custom player models?
**A**: Yes! The addon injects bytecode into `PlayerEntityExtendedModelMixin` and `PlayerArmorExtendedModelMixin`, allowing player characters using MCA Reborn's custom player model system to fully utilize all scaling, position, and rotation parameters.

#### Q3: Does the addon impose arbitrary limits on slider values?
**A**: No. In strict compliance with the **Player Agency & Anti-Nanny Invariant**, sliders allow full granular scaling from `0%` to `100%` (`4.44x` scale), 3-axis position offsets, and $360^\circ$ total Euler rotation without artificial ceilings.

---

## 🔗 Related Pages
* [[Return to Global Home|Home]]
* [[Version Compatibility Matrix|Version-Compatibility]]
* [[Developer Setup & Building Guide|Developer-Setup-and-Building]]
