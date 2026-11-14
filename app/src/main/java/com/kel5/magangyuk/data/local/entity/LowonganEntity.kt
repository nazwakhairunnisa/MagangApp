package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "lowongan": satu baris = satu lowongan magang milik satu mitra
@Entity(
    tableName = "lowongan",
    foreignKeys = [
        ForeignKey(
            entity = MitraEntity::class,
            parentColumns = ["userId"],
            childColumns = ["mitraId"],
            // Lowongan yang sudah punya pelamar tidak dihapus, hanya ditutup
            // (aturan itu dijaga di Repository, bukan di sini)
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("mitraId")]
)
data class LowonganEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // Menunjuk ke mitra.userId
    val mitraId: Long,
    val posisi: String,
    val divisi: String,
    val deskripsi: String,
    val persyaratan: String,
    // Boleh kosong, dipakai untuk filter dan rekomendasi
    val jurusan: String? = null,
    val kuota: Int,
    val lokasi: String,
    val mode: ModeKerja,
    val jenis: JenisLowongan,
    // Format tanggal: "yyyy-MM-dd"
    val tglMulai: String,
    val tglSelesai: String,
    val batasDaftar: String,
    val pembimbingLapangan: String,
    val wajibKtm: Boolean = false,
    val wajibLaporanMitra: Boolean = false,
    // true kalau mitra menutup lowongan sendiri
    val ditutupManual: Boolean = false
)