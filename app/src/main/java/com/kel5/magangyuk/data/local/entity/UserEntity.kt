package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "users": satu baris = satu akun (mahasiswa, mitra, atau dosen)
@Entity(
    tableName = "users",
    // loginId (NIM/email/NIDN) harus unik supaya tidak ada dua akun yang sama
    indices = [Index(value = ["loginId"], unique = true)]
)
data class UserEntity(
    // Kunci utama, diisi otomatis oleh Room (1, 2, 3, ...)
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val role: Role,
    val loginId: String,
    // Yang disimpan hash-nya, bukan password asli
    val passwordHash: String
)