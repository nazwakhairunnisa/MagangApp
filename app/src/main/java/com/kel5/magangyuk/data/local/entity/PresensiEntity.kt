package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "presensi": satu baris = satu kali hadir di satu hari (tanpa absen pulang)
@Entity(
    tableName = "presensi",
    foreignKeys = [
        ForeignKey(
            entity = MagangEntity::class,
            parentColumns = ["id"],
            childColumns = ["magangId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Unik gabungan: satu magang hanya boleh punya satu presensi per tanggal
    indices = [Index(value = ["magangId", "tanggal"], unique = true)]
)
data class PresensiEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val magangId: Long,
    // Format: "yyyy-MM-dd"
    val tanggal: String,
    // Format: "HH:mm"
    val jamHadir: String,
    // Teks lokasi hasil dari GPS
    val lokasi: String
)