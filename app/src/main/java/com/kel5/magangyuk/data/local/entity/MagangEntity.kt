package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "magang": satu baris = satu magang yang sedang/sudah berjalan
@Entity(
    tableName = "magang",
    foreignKeys = [
        ForeignKey(
            entity = LamaranEntity::class,
            parentColumns = ["id"],
            childColumns = ["lamaranId"],
            onDelete = ForeignKey.CASCADE
        ),
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
        ),
        ForeignKey(
            entity = DosenEntity::class,
            parentColumns = ["userId"],
            childColumns = ["dosenId"],
            // Kalau dosen dihapus, magang tetap ada, dosenId jadi kosong
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        // Satu lamaran hanya menghasilkan satu magang
        Index(value = ["lamaranId"], unique = true),
        Index("mahasiswaId"),
        Index("lowonganId"),
        Index("dosenId")
    ]
)
data class MagangEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val lamaranId: Long,
    val mahasiswaId: Long,
    val lowonganId: Long,
    // Dosen pembimbing, boleh kosong kalau belum ditetapkan
    val dosenId: Long? = null
)