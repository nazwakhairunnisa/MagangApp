package com.kel5.magangyuk.data.local.entity

// Peran pengguna, dipakai di tabel users
enum class Role { MAHASISWA, MITRA, DOSEN }

// Mode kerja lowongan
enum class ModeKerja { ONSITE, HYBRID, ONLINE }

// Jenis lowongan
enum class JenisLowongan { PAID, UNPAID }

// Status lamaran, urutannya mengikuti alur di spesifikasi
enum class StatusLamaran {
    MENUNGGU, DITERIMA, DIKONFIRMASI, DITOLAK, DIBATALKAN, KEDALUWARSA
}

// Jenis notifikasi, sesuai tabel event di data model
enum class TipeNotifikasi {
    LAMARAN_BARU, LAMARAN_DITERIMA, LAMARAN_DITOLAK,
    TAWARAN_DIKONFIRMASI, TAWARAN_DITOLAK, TAWARAN_KEDALUWARSA,
    LAPORAN_DIUNGGAH, LAPORAN_DISETUJUI, LAPORAN_REVISI,
    NILAI_MITRA_TERKIRIM, NILAI_DOSEN_TERKIRIM, MAGANG_SELESAI

}

// Status review laporan akhir, dipakai oleh dosen dan mitra
enum class StatusReview { MENUNGGU, REVISI, DISETUJUI }