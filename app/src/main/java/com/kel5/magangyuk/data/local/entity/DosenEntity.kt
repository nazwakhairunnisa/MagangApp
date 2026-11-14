package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabel "dosen": data dosen pembimbing, tersambung ke satu akun di tabel users
@Entity(
    tableName = "dosen",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DosenEntity(
    @PrimaryKey val userId: Long,
    val nama: String,
    val nidn: String,
    val jurusan: String,
    val telepon: String,
    val email: String
)