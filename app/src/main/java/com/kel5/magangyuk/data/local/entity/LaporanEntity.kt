package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "laporan": laporan akhir, satu file per magang
@Entity(
    tableName = "laporan",
    foreignKeys = [
        ForeignKey(
            entity = MagangEntity::class,
            parentColumns = ["id"],
            childColumns = ["magangId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Unik: satu magang hanya punya satu laporan (unggah ulang = update baris ini)
    indices = [Index(value = ["magangId"], unique = true)]
)
data class LaporanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val magangId: Long,
    val judul: String,
    // Path file PDF di penyimpanan internal app
    val filePath: String,
    // Catatan singkat dari mahasiswa, opsional
    val catatan: String? = null,
    // Waktu dalam milidetik
    val tglUnggah: Long,
    // Review dosen (wajib)
    val statusDosen: StatusReview = StatusReview.MENUNGGU,
    // Terisi saat dosen meminta revisi
    val catatanDosen: String? = null,
    // Review mitra. Tetap disimpan walau lowongan tidak mewajibkan,
    // tampil atau tidaknya diatur di UI lewat lowongan.wajibLaporanMitra
    val statusMitra: StatusReview = StatusReview.MENUNGGU,
    val catatanMitra: String? = null
)