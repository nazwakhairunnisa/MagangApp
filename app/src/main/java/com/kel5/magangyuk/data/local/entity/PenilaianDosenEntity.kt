package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabel "penilaian_dosen": nilai dari dosen pembimbing, satu baris per magang
@Entity(
    tableName = "penilaian_dosen",
    foreignKeys = [
        ForeignKey(
            entity = MagangEntity::class,
            parentColumns = ["id"],
            childColumns = ["magangId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PenilaianDosenEntity(
    // Kunci utama sekaligus penunjuk ke magang: satu magang, satu penilaian dosen
    @PrimaryKey val magangId: Long,
    // 0-100 (divalidasi di ViewModel)
    val nilaiAkademik: Int,
    // Wajib diisi
    val feedback: String,
    // Waktu dalam milidetik, diperbarui kalau dosen mengedit
    val tglKirim: Long
)