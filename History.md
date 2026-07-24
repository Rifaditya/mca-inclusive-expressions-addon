# Project Version History: MCA Inclusive Expressions

This document tracks all build releases and incremental updates for the MCA Inclusive Expressions project.

---

### Release `3.5.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.5.0+26.2.jar`
* **Changes**:
  - Restored MCA's native top tabs (General, Character, Personality, Traits, Debug), preview models, and localized headers. Refactored trait list rendering in onSetPageTail to 6 traits per page with Full-Chested at Index 0, guaranteeing zero bottom cutoff on windowed screens.

---

### Release `3.4.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.4.0+26.2.jar`
* **Changes**:
  - Intercepted setPage("traits") at HEAD to place Full-Chested at Index 0 in the dynamic traits list. Cleans up pagination math so exactly 8 visible traits render per page across Page 1, Page 2, and Page 3 with zero hidden buttons or layout overflow.

---

### Release `3.3.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.3.0+26.2.jar`
* **Changes**:
  - Positioned Full-Chested as the very first trait (Slot 0 at top of Page 1) above Lactose Intolerance, shifting native Page 1 trait buttons down by 22px with zero gap.

---

### Release `3.2.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.2.0+26.2.jar`
* **Changes**:
  - Implemented resolution-agnostic dynamic button discovery (lastButton.getY() + 22) on Page 3 (this.traitPage == 2). Automatically attaches Full-Chested directly below No Aging with zero gap across all window sizes, display resolutions, and GUI scaling factors, with dynamic cleanup on page flips.

---

### Release `3.1.9+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.9+26.2.jar`
* **Changes**:
  - Purged all custom mixin trait button overlays and re-alignment loops from onSetPageTail. MCA's native trait list engine handles FULL_CHESTED_TRAIT 100% natively via @Overwrite getValidTraits(), eliminating page-overlay bugs.

---

### Release `3.1.8+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.8+26.2.jar`
* **Changes**:
  - Implemented sequential post-layout Y re-alignment pass (widget.setY(64 + slot * 22)) on the traits tab. Eliminates all skipped slots and gaps, packing Full-Chested 100% tightly directly beneath No Aging at Y = 174 on Page 3.

---

### Release `3.1.7+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.7+26.2.jar`
* **Changes**:
  - Cleaned mixins.json to valid SpongePowered Mixin schema and changed getValidTraits visibility to protected for Mixin Overwrite. Added fail-safe button placement in onSetPageTail at Y = 174 (zero gap under No Aging).

---

### Release `3.1.6+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.6+26.2.jar`
* **Changes**:
  - Used Overwrite on getValidTraits in VillagerEditorScreenMixin to return Traits.TRAIT_REGISTRY.values(). MCA's native GUI loop renders Full-Chested as native slot #6 on Page 3 right below No Aging with zero gaps.

---

### Release `3.1.5+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.5+26.2.jar`
* **Changes**:
  - Injected getValidTraits via VillagerEditorScreenMixin to return Traits.TRAIT_REGISTRY.values(). MCA's native GUI loop now handles creating, positioning, and toggling Full-Chested automatically.

---

### Release `3.1.4+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.4+26.2.jar`
* **Changes**:
  - Corrected Full-Chested button Y-positioning using MCA Reborn's exact native list formula 64 + (nativeCount * 22). Snaps Full-Chested directly beneath No Aging at Y = 174 on Page 3 for NPC Villagers and directly beneath Infertile at Y = 152 on Page 2 for Player Characters.

---

### Release `3.1.3+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.3+26.2.jar`
* **Changes**:
  - Added direct Full-Chested trait button placement inside onSetPageTail when the traits tab is rendered. Places Full-Chested at Slot 6 on Page 3 for NPC Villagers and Slot 5 on Page 2 for Player Characters.

---

### Release `3.1.2+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.2+26.2.jar`
* **Changes**:
  - Intercepted getValidTraits to query Traits.TRAIT_REGISTRY.values() instead of enum values. Full-Chested displays on Page 3 for NPC Villagers and Page 2 for Player Characters.

---

### Release `3.1.1+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.1+26.2.jar`
* **Changes**:
  - Rely 100% on native MCA trait registration system, removing getValidTraits Mixin override. Added trait.full_chested translation key.

---

### Release `3.1.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.1.0+26.2.jar`
* **Changes**:
  - Registered custom Full-Chested MCA Trait (trait.mca.full_chested) and full_chested_trait_chance GameRule (range 0-100%, default 5%).

---

### Release `3.0.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-3.0.0+26.2.jar`
* **Changes**:
  - Resolved male character model breast rendering across client and server. Updated isAllowAllGenders() to resolve client level/singleplayer GameRules on the client rendering thread, and removed !part.visible skip condition in CommonVillagerInterfaceMixin.onRenderCommon.

---

### Release `2.9.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.9.0+26.2.jar`
* **Changes**:
  - Overrode MCA's default male breast hiding behavior in CommonVillagerInterfaceMixin.onRenderCommon when allow_all_genders GameRule is active, rendering custom 3D breasts on male characters in real-time.

---

### Release `2.8.0+26.2` (2026-07-24)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.8.0+26.2.jar`
* **Changes**:
  - Implemented copyCommonAttributes synchronization across CommonVillagerInterfaceMixin to guarantee 100% 1:1 scale, position, and rotation parity between armor models and unarmored body models.
  - Removed Gender Inclusivity button from VillagerEditorScreen GUI to rely strictly on server GameRule.

---

### Release `2.7.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.7.0+26.2.jar`
* **Changes**:
  - Configured direct linear 3D MatrixStack scaling without hidden multiplication formulas. 100% on the Size slider maps directly to 5.0x maximum volume scale (0% = 0.0x, 20% = 1.0x native default, 50% = 2.5x, 100% = 5.0x).

---

### Release `2.6.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.6.0+26.2.jar`
* **Changes**:
  - Reverted custom scale multiplier adders (2.0x scaling multipliers and >100% max scale limits). Size sliders now run strictly from 0% to 100% (aligning 100% size 1:1 with native MCA default maximum volume).
  - Scoped GUI Direct Screen Override (guiDuck) strictly to villagerVisualization's model in VillagerEditorScreen, preventing editor slider settings from bleeding into Paper Dolls, Inventory View, or in-world entities.
  - Expanded VillagerVisualsMixin to use VillagerLike.toVillager(entity) for both Villagers and Players, ensuring 100% 1:1 visual parity across Real World 3D entities, Villager Editor GUI Settings, Paper Dolls HUD, and Inventory Character View.
  - Updated VillagerEntityMCAMixin to unpack tag.getCompound("MCAData"), guaranteeing full NBT save persistence upon pressing "Done".

---

### Release `2.5.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.5.0+26.2.jar`
* **Changes**:
  - Eliminated global static variable mutations when dragging sliders in VillagerEditorScreen. Sliders now mutate ONLY the target entity's GeneticsDuck instance, guaranteeing 100% per-character genetic isolation!
  - Created VillagerEntityMCAMixin to inject into readAdditionalSaveData, readAdditionalSaveDataForEditor, and addAdditionalSaveData on VillagerEntityMCA. All 14 3D parameters are now written directly to and loaded from world NBT on both server and client!

---

### Release `2.4.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.4.1+26.2.jar`
* **Changes**:
  - Created ExtendedSliderWidgetMixin to cancel inline tooltip rendering in ExtendedSliderWidget during the widget draw pass, delegating tooltips to Minecraft's top-level this.setTooltip(Tooltip.create(text)) pipeline. Tooltips now render on the topmost foreground layer above all sliders and buttons with zero clipping or text hiding!

---

### Release `2.4.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.4.0+26.2.jar`
* **Changes**:
  - Calibrated 100% Breast Size slider to render at 2.0x base scale multiplier (0% = 0.0x, 50% = 1.0x, 100% = 2.0x, 200% = 4.0x, 500% = 10.0x), providing double standard volume at default 100% size.

---

### Release `2.3.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.3.1+26.2.jar`
* **Changes**:
  - Registered custom GameRuleCategory using Identifier.fromNamespaceAndPath(MOD_ID, "category"), grouping all 3 mod GameRules under a bolded custom header (▼ MCA Inclusive Expressions) matching vanilla Minecraft design.
  - Added src/main/resources/assets/mca_inclusive_expressions_addon/lang/en_us.json, providing clean localized titles and hover tooltips for Chest Scale Multiplier (%), Max Chest Scale Limit (%), and Gender Inclusivity (All Genders).

---

### Release `2.3.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.3.0+26.2.jar`
* **Changes**:
  - Decoupled 3D position translations (Left & Right X, Y, Z) from 3D rotation angles (Pitch, Yaw, Roll, MCA native tilt) by un-rotating native model tilt before translating. Position sliders now move in 100% pure orthogonal world space (Up is Up, Left is Left, Forward is Forward) with zero diagonal movement!

---

### Release `2.2.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.2.0+26.2.jar`
* **Changes**:
  - Injected into VillagerEditorScreen.createEditorData() to package all 14 3D parameters into the outgoing C2S network packet, and created VillagerEditorSyncRequestMixin to whitelist mca_inclusive_expressions:* keys on the server. This guarantees permanent save persistence for both Villagers and Player models upon pressing the "Done" button!

---

### Release `2.1.4+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.4+26.2.jar`
* **Changes**:
  - Created VillagerEditorScreenAccess interface and moved getActiveGuiGenetics() helper to MCAInclusiveExpressionsAddon, removing all non-private static members from VillagerEditorScreenMixin and resolving InvalidMixinException startup crash.

---

### Release `2.1.3+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.3+26.2.jar`
* **Changes**:
  - Relocated activeEditorScreen static reference to MCAInclusiveExpressionsAddon, satisfying Fabric Mixin static field rules and resolving InvalidMixinException startup crash.

---

### Release `2.1.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.2+26.2.jar`
* **Changes**:
  - Implemented live GUI screen genetics override in CommonVillagerInterfaceMixin.onRenderCommon, allowing the renderer to read 3D parameters directly from VillagerEditorScreen's active genetics during GUI rendering. This provides 100% real-time 1:1 synchronization between GUI sliders, the 3D preview model, and the in-world entity!

---

### Release `2.1.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.1+26.2.jar`
* **Changes**:
  - Implemented automatic genetics parameter synchronization from villager to villagerVisualization upon GUI screen initialization (init & setPage), eliminating the visual disconnect between real in-world entity size and GUI 3D preview model size.

---

### Release `2.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.1.0+26.2.jar`
* **Changes**:
  - Organized the Breast tab into 3 nested sub-categories ([ Size ], [ Position ], [ Rotation ]).
  - Added 3-axis 3D Euler angle rotation sliders (Pitch X, Yaw Y, Roll Z) for Left & Right breasts with NBT persistence and MatrixStack quaternion transformation.

---

### Release `2.0.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.2+26.2.jar`
* **Changes**:
  - Implemented local pivot-centered matrix stack transformations ((-1.75f, 0.25f, 0.0f) for Left and (+1.75f, 0.25f, 0.0f) for Right) so Size sliders modify volume ONLY with zero position drift across the body.

---

### Release `2.0.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.1+26.2.jar`
* **Changes**:
  - Calibrated 1:1 normal scale calculation, removing MCA's internal getBreastSize() * getBreasts() sub-fraction multiplication (0.05f) so 99%/100% renders at full 1.0x normal size and 500% renders at 5.0x massive size.

---

### Release `2.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-2.0.0+26.2.jar`
* **Changes**:
  - Implemented direct 1:1 linear size matrix scaling (100% = 1.0x, 500% = 5.0x, 1000% = 10.0x).
  - Migrated Max Scale Limit setting out of the character GUI into a server GameRule (mca_inclusive_expressions:max_scale_limit, range 100-2000, default 500%).
  - Cleaned up Row 5 in character editor GUI by expanding Slider Link Mode button to full width.

---

### Release `1.9.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.9.0+26.2.jar`
* **Changes**:
  - Implemented VillagerRenderStateDuck to transfer 8 3D parameters per villager into VillagerRenderState, eliminating global static variable pollution during rendering.
  - Added 6 independent 3D position sliders (Left X/Y/Z and Right X/Y/Z) in a 2-column 6-row GUI layout.
  - Restored MCA native chest alignment.

---

### Release `1.8.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.8.5+26.2.jar`
* **Changes**:
  - Centered 3D MatrixStack scaling around Left Breast's pivot center (-1.75F, 0.25F, 0.0F) and Right Breast's pivot center (+1.75F, 0.25F, 0.0F) for 100% total 3D mesh isolation.

---

### Release `1.8.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.8.0+26.2.jar`
* **Changes**:
  - Implemented GeneticsDuck interface on MCA's Genetics class to give every villager two 100% independent genetic breast properties (leftBreastSize and rightBreastSize) with full NBT persistence, genetic inheritance, and independent 3D MatrixStack scaling.

---

### Release `1.7.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.5+26.2.jar`
* **Changes**:
  - Restored MCA's native -35 degree breast rotation angle via part.translateAndRotate(matrices) and scaled cubes.get(0) with leftBreastSize matrix and cubes.get(1) with rightBreastSize matrix.

---

### Release `1.7.4+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.4+26.2.jar`
* **Changes**:
  - Fixed method signature descriptor of onBodyData in VillagerEntityModelMixin, resolving InvalidInjectionException on startup.

---

### Release `1.7.3+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.3+26.2.jar`
* **Changes**:
  - Implemented failsafe dual breast children architecture (breasts.getChild("left") and breasts.getChild("right")) across VillagerEntityBaseModelMCA and VillagerEntityModelMCA with automatic fallback rendering.

---

### Release `1.7.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.2+26.2.jar`
* **Changes**:
  - Implemented CommonVillagerModelAccess interface and bound leftBreastPart and rightBreastPart to renderCommon for true 100% independent left and right 3D cube scaling.

---

### Release `1.7.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.1+26.2.jar`
* **Changes**:
  - Resolved startup InvalidInjectionException by creating CommonVillagerInterfaceMixin targeting CommonVillagerModel interface for renderCommon.

---

### Release `1.7.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.7.0+26.2.jar`
* **Changes**:
  - Upgraded to primary MCA Core Enhancement Mod (`MCA Inclusive Expressions`).

---

### Release `1.6.5+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.5+26.2.jar`
* **Changes**:
  - Injected directly into static `VillagerEntityBaseModelMCA.newBreasts` to bake 2 distinct 3x3x3 cubes across all model subclasses (`VillagerEntityModelMCA`, `PlayerEntityExtendedModel`, armor meshes).

---

### Release `1.6.4+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.4+26.2.jar`
* **Changes**:
  - Assigned separate pivot offsets to left_breast (X = -1.75F) and right_breast (X = +1.75F), maintaining a permanent cleavage gap and preventing mesh merging at 200%+ scales.

---

### Release `1.6.3+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.3+26.2.jar`
* **Changes**:
  - Fixed render gate condition in `CommonVillagerModelMixin` by keeping container `breasts.visible = true`.

---

### Release `1.6.2+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.2+26.2.jar`
* **Changes**:
  - Implemented independent 3D ModelParts (`left_breast` & `right_breast`) for true 100% individual cube scaling.

---

### Release `1.6.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.1+26.2.jar`
* **Changes**:
  - Injected into `VillagerEntityBaseModelMCA.newBreasts` to split MCA's single 6x3x3 box into two distinct 3x3x3 cubes (Left & Right Breast) for true 2-rectangle 3D model rendering.

---

### Release `1.6.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.6.0+26.2.jar`
* **Changes**:
  - Implemented independent Left Breast & Right Breast controls with linked/unlinked asymmetry modes.
  - Set default slider range to 0%-200% (default 100%).
  - Added customizable Max Scale Limit toggle button (200%, 300%, 500%, 1000%).

---

### Release `1.5.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.5.1+26.2.jar`
* **Changes**:
  - Extended Breast Size slider minimum range from 10% to 0% (0% to 1000%) across GUI, GameRules, and ModMenu config screen for flat chest customization.

---

### Release `1.5.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.5.0+26.2.jar`
* **Changes**:
  - Unified base genetics `Breast` slider and `Chest Scale` multiplier into ONE single `Breast Size: X%` slider (10% to 1000%) in the `Breast` sub-tab.

---

### Release `1.4.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.4.1+26.2.jar`
* **Changes**:
  - Removed duplicate `Breast` gene slider from MCA's `Body` sub-tab.

---

### Release `1.4.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.4.0+26.2.jar`
* **Changes**:
  - Redid and streamlined core codebase: removed dual breast models and cleavage angle calculations.

---

### Release `1.3.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.1+26.2.jar`
* **Changes**:
  - Implemented real-time preview model dimension refresh (`villager.refreshDimensions()`).

---

### Release `1.3.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.3.0+26.2.jar`
* **Changes**:
  - Added dedicated `Breast` sub-tab under `Character` (`Body`, `Clothes`, `Hair`, `Eyes`, `Breast`).

---

### Release `1.2.1+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.1+26.2.jar`
* **Changes**:
  - Fixed GUI slider overlap in `VillagerEditorScreen`.

---

### Release `1.2.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.2.0+26.2.jar`
* **Changes**:
  - Implemented `VillagerEditorScreenMixin` to inject `IntegerSliderWidget` into MCA's `VillagerEditorScreen`.

---

### Release `1.1.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.1.0+26.2.jar`
* **Changes**:
  - Integrated ModMenu & YACL config GUI (`ModMenuIntegration`, `ConfigScreenHelper`).

---

### Release `1.0.0+26.2` (2026-07-23)
* **Target Game Release**: Minecraft 26.2+
* **Build Artifact**: `mca-inclusive-expressions-addon-1.0.0+26.2.jar`
* **Changes**:
  - Initial project release with 2.0x chest scaling and gender-inclusive body representation GameRules.
