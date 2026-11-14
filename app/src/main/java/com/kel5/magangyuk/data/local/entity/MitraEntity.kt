package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabel "mitra": profil perusahaan, tersambung ke satu akun di tabel users
@Entity(
    tableName = "mitra",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MitraEntity(
    @PrimaryKey val userId: Long,
    val namaPerusahaan: String,
    val bidangUsaha: String,
    // Alamat boleh diedit mitra (spesifikasi E.1)
    val alamat: String,
    val deskripsi: String,
    val telepon: String,
    val email: String,
    // Tanda "?" artinya boleh kosong (null), karena website opsional
    val website: String? = null
)