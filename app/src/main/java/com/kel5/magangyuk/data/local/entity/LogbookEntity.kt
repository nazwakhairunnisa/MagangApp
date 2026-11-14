package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "logbook": catatan kegiatan harian magang
@Entity(
    tableName = "logbook",
    foreignKeys = [
        ForeignKey(
            entity = MagangEntity::class,
            parentColumns = ["id"],
            childColumns = ["magangId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Unik gabungan: satu magang hanya boleh punya satu entri logbook per tanggal
    indices = [Index(value = ["magangId", "tanggal"], unique = true)]
)
data class LogbookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val magangId: Long,
    // Format: "yyyy-MM-dd"
    val tanggal: String,
    // Wajib, minimal 10 karakter (divalidasi di ViewModel, bukan di sini)
    val deskripsi: String,
    // Format: "HH:mm"
    val jamMasuk: String,
    // Harus setelah jamMasuk (divalidasi di ViewModel)
    val jamKeluar: String
)