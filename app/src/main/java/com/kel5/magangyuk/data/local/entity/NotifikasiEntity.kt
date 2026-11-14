package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "notifikasi": pusat notifikasi dalam app, satu baris = satu notifikasi untuk satu penerima
@Entity(
    tableName = "notifikasi",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Index gabungan untuk menghitung badge: count notifikasi milik user dengan dibaca = false
    indices = [Index(value = ["userId", "dibaca"])]
)
data class NotifikasiEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // Penerima notifikasi (menunjuk ke users.id)
    val userId: Long,
    val tipe: TipeNotifikasi,
    val judul: String,
    val pesan: String,
    // lamaranId untuk tipe LAMARAN_* dan TAWARAN_*,
    // magangId untuk tipe LAPORAN_*, NILAI_*, dan MAGANG_SELESAI
    val refId: Long,
    val dibaca: Boolean = false,
    // Waktu dalam milidetik
    val tglBuat: Long
)