package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabel "mahasiswa": data diri, tersambung ke satu akun di tabel users
@Entity(
    tableName = "mahasiswa",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MahasiswaEntity(
    // Sekaligus kunci utama: satu akun hanya punya satu data mahasiswa
    @PrimaryKey val userId: Long,
    val nama: String,
    val nim: String,
    val prodi: String,
    val fakultas: String,
    val telepon: String,
    val email: String,
    // Id dosen pembimbing. Belum dihubungkan ke tabel dosen (belum dibuat),
    // nullable karena bisa saja belum ditetapkan
    val dosenId: Long? = null
)