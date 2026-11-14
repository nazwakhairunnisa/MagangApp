package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

// Tabel "bookmark": lowongan yang disimpan mahasiswa
@Entity(
    tableName = "bookmark",
    // Kunci utama gabungan: pasangan yang sama tidak bisa tersimpan dua kali
    primaryKeys = ["mahasiswaId", "lowonganId"],
    foreignKeys = [
        ForeignKey(
            entity = MahasiswaEntity::class,
            parentColumns = ["userId"],
            childColumns = ["mahasiswaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LowonganEntity::class,
            parentColumns = ["id"],
            childColumns = ["lowonganId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("lowonganId")]
)
data class BookmarkEntity(
    val mahasiswaId: Long,
    val lowonganId: Long
)