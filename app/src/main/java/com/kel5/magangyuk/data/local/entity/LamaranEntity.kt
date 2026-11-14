package com.kel5.magangyuk.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabel "lamaran": satu baris = satu mahasiswa melamar satu lowongan
@Entity(
    tableName = "lamaran",
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
    indices = [
        // Unik gabungan: mahasiswa yang sama tidak bisa melamar lowongan yang sama dua kali
        Index(value = ["mahasiswaId", "lowonganId"], unique = true),
        Index("lowonganId")
    ]
)
data class LamaranEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    // Menunjuk ke mahasiswa.userId
    val mahasiswaId: Long,
    val lowonganId: Long,
    // Waktu dalam milidetik (System.currentTimeMillis())
    val tglKirim: Long,
    val status: StatusLamaran = StatusLamaran.MENUNGGU,
    // Terisi saat mitra menekan Terima, dipakai untuk menghitung batas 3 hari
    val tglDiterima: Long? = null,
    val telepon: String,
    // Path file CV di penyimpanan internal app
    val cvPath: String,
    // Opsional, wajib hanya jika lowongan mengaktifkan "Wajib KTM"
    val ktmPath: String? = null
)