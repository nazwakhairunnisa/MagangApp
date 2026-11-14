package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// Tabel "penilaian_mitra": nilai dari mitra, satu baris per magang
@Entity(
    tableName = "penilaian_mitra",
    foreignKeys = [
        ForeignKey(
            entity = MagangEntity::class,
            parentColumns = ["id"],
            childColumns = ["magangId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PenilaianMitraEntity(
    // Kunci utama sekaligus penunjuk ke magang: satu magang, satu penilaian mitra
    @PrimaryKey val magangId: Long,
    // Empat aspek, masing-masing 0-100 (divalidasi di ViewModel)
    val kedisiplinan: Int,
    val kerjaSama: Int,
    val keterampilan: Int,
    val hasilKerja: Int,
    // Wajib diisi
    val catatan: String,
    // Waktu dalam milidetik, diperbarui kalau mitra mengedit
    val tglKirim: Long
)