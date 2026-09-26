# Wiki Resmi MCA Inclusive Expressions Addon

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi dalam Wiki ini mencerminkan **kondisi kode sumber terbaru dalam repositori**, yang mungkin mencakup komit pengembangan sebelum rilis publik di CurseForge dan Modrinth.

Selamat datang di dokumentasi teknis dan panduan resmi **MCA Inclusive Expressions**, ekspansi untuk Minecraft Comes Alive (MCA Reborn) yang menghadirkan kustomisasi 3D independen, penyesuaian posisi ortogonal, dan kontrol editor penduduk desa.

---

## 🧭 Portal Navigasi Versi

| Minecraft Version | Mod Version | Dedicated Portal Link |
| :--- | :--- | :--- |
| **Minecraft 26.2** | `v4.5.1+26.2` | [[👉 Minecraft 26.2|id_id-26.2-Home]] |
| **Minecraft 26.3** | `v4.5.1+26.3` | [[👉 Minecraft 26.3|id_id-26.3-Home]] |

---

## 🌟 Fitur Utama & Mekanisme Gameplay

- **Skala 3D MatrixStack Independen**: Penyesuaian volume dada kiri dan kanan dari 0.0x hingga 4.44x dengan distribusi kurva lonceng Gaussian dan asimetri alami (±1%).
- **Posisi Ortogonal 6-Aksis**: Translasi 3D murni tanpa terpengaruh kemiringan bawaan MCA -35° (Atas adalah Atas, Kiri adalah Kiri, Depan adalah Depan).
- **Kontrol Rotasi Euler 3D**: Pengaturan sudut Pitch, Yaw, dan Roll yang presisi.
- **Mesin Anti-Tembus TorsoClippingVertexConsumer**: Menggunakan transformasi matriks terbalik untuk mencegah geometri menembus punggung karakter pada skala besar.
- **Antarmuka Editor Penduduk Desa**: Tab ke-5 [ Dada ] dengan 3 sub-kategori: [ Ukuran ], [ Posisi ], dan [ Rotasi ].
- **GameRules Server**: Pengaturan probabilitas kemunculan melalui `force_all_breasted` dan `full_chested_trait_chance`.

---

## 📚 Matriks Kompatibilitas Versi & FAQ
* [[Matriks Kompatibilitas Versi|id_id-Version-Compatibility]]
* [[Pemecahan Masalah & Pertanyaan yang Sering Diajukan (FAQ)|id_id-Troubleshooting-and-FAQ]]
* [[Global English Portal|Home]]

---

*Author: **Dasik (Rifaditya)** | License: **GNU General Public License v3.0 (GPLv3)***
