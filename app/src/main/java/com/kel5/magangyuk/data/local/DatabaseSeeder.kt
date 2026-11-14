package com.kel5.magangyuk.data.local

import com.kel5.magangyuk.data.local.entity.DosenEntity
import com.kel5.magangyuk.data.local.entity.JenisLowongan
import com.kel5.magangyuk.data.local.entity.LowonganEntity
import com.kel5.magangyuk.data.local.entity.MahasiswaEntity
import com.kel5.magangyuk.data.local.entity.MitraEntity
import com.kel5.magangyuk.data.local.entity.ModeKerja
import com.kel5.magangyuk.data.local.entity.Role
import com.kel5.magangyuk.data.local.entity.UserEntity
import com.kel5.magangyuk.util.PasswordHasher
import java.time.LocalDate

// Data masih dummy dan sewaktu-waktu ada data asli tinggal ubah saja
object DatabaseSeeder {
    // Password dummy untuk semua akun seed, hanya untuk demo/tugas
    private const val DEFAULT_PASSWORD = "password123"

    // suspend = harus dipanggil dari coroutine (akses database tidak boleh di thread utama)
    suspend fun seedIfEmpty(db: AppDatabase) {
        // Kalau sudah ada akun, berarti seed pernah jalan: jangan isi lagi
        if (db.userDao().count() > 0) return

        // seedAkun mengembalikan id dua mitra, dipakai untuk membuat lowongan
        val (mitra1, mitra2) = seedAkun(db)
        seedLowongan(db, mitra1, mitra2)
    }

    private suspend fun seedAkun(db: AppDatabase): Pair<Long, Long> {
        // 1. Dosen dulu, supaya id-nya bisa dipakai sebagai dosenId mahasiswa
        val dosenId = buatUser(db, Role.DOSEN, "0101018801")
        db.dosenDao().insert(
            DosenEntity(
                userId = dosenId,
                nama = "Dr. Rina Lestari, M.Kom.",
                nidn = "0101018801",
                jurusan = "Teknologi Informasi",
                telepon = "081200000001",
                email = "rina.lestari@kampus.ac.id"
            )
        )

        // 2. Mahasiswa
        val mhs1 = buatUser(db, Role.MAHASISWA, "221402001")
        db.mahasiswaDao().insert(
            MahasiswaEntity(
                userId = mhs1,
                nama = "Budi Santoso",
                nim = "221402001",
                prodi = "Teknologi Informasi",
                fakultas = "Ilmu Komputer dan Teknologi Informasi",
                telepon = "081211110001",
                email = "budi.santoso@students.kampus.ac.id",
                dosenId = dosenId
            )
        )
        val mhs2 = buatUser(db, Role.MAHASISWA, "221402002")
        db.mahasiswaDao().insert(
            MahasiswaEntity(
                userId = mhs2,
                nama = "Sari Wulandari",
                nim = "221402002",
                prodi = "Teknologi Informasi",
                fakultas = "Ilmu Komputer dan Teknologi Informasi",
                telepon = "081211110002",
                email = "sari.wulandari@students.kampus.ac.id",
                dosenId = dosenId
            )
        )

        // 3. Mitra (perusahaan)
        val mitra1 = buatUser(db, Role.MITRA, "hrd@teknusa.co.id")
        db.mitraDao().insert(
            MitraEntity(
                userId = mitra1,
                namaPerusahaan = "PT Teknologi Nusantara",
                bidangUsaha = "Pengembangan Perangkat Lunak",
                alamat = "Jl. Gatot Subroto No. 10, Medan",
                deskripsi = "Perusahaan pengembang aplikasi mobile dan web.",
                telepon = "061111111",
                email = "hrd@teknusa.co.id",
                website = "https://teknusa.co.id"
            )
        )
        val mitra2 = buatUser(db, Role.MITRA, "hrd@mediakreatif.co.id")
        db.mitraDao().insert(
            MitraEntity(
                userId = mitra2,
                namaPerusahaan = "CV Media Kreatif",
                bidangUsaha = "Desain dan Multimedia",
                alamat = "Jl. Sisingamangaraja No. 25, Medan",
                deskripsi = "Studio desain grafis dan produksi konten digital.",
                telepon = "061222222",
                email = "hrd@mediakreatif.co.id",
                website = null
            )
        )

        return Pair(mitra1, mitra2)
    }

    // Contoh lowongan. Tanggal dihitung dari hari ini supaya lowongan selalu berstatus Aktif.
    private suspend fun seedLowongan(db: AppDatabase, mitra1: Long, mitra2: Long) {
        val today = LocalDate.now()
        // Batas daftar harus sebelum tanggal mulai (aturan spesifikasi E.3)
        val batasDaftar = today.plusDays(14).toString()
        val tglMulai = today.plusDays(21).toString()
        val tglSelesai = today.plusDays(21 + 60).toString() // durasi sekitar 2 bulan

        db.lowonganDao().insert(
            LowonganEntity(
                mitraId = mitra1,
                posisi = "Android Developer Intern",
                divisi = "Mobile Development",
                deskripsi = "Membantu tim membangun dan merawat aplikasi Android.",
                persyaratan = "Menguasai dasar Kotlin dan Jetpack Compose.",
                jurusan = "Teknologi Informasi",
                kuota = 2,
                lokasi = "Medan",
                mode = ModeKerja.HYBRID,
                jenis = JenisLowongan.PAID,
                tglMulai = tglMulai,
                tglSelesai = tglSelesai,
                batasDaftar = batasDaftar,
                pembimbingLapangan = "Andi Pratama",
                wajibKtm = true,
                wajibLaporanMitra = true
            )
        )
        db.lowonganDao().insert(
            LowonganEntity(
                mitraId = mitra1,
                posisi = "Backend Developer Intern",
                divisi = "Web Development",
                deskripsi = "Membuat dan menguji REST API untuk produk web perusahaan.",
                persyaratan = "Memahami dasar database dan REST API.",
                jurusan = "Teknologi Informasi",
                kuota = 1,
                lokasi = "Medan",
                mode = ModeKerja.ONSITE,
                jenis = JenisLowongan.PAID,
                tglMulai = tglMulai,
                tglSelesai = tglSelesai,
                batasDaftar = batasDaftar,
                pembimbingLapangan = "Dewi Anggraini"
            )
        )
        db.lowonganDao().insert(
            LowonganEntity(
                mitraId = mitra2,
                posisi = "UI/UX Designer Intern",
                divisi = "Desain",
                deskripsi = "Membuat desain antarmuka dan prototipe untuk proyek klien.",
                persyaratan = "Bisa memakai Figma dan memahami dasar UX.",
                jurusan = null, // terbuka untuk semua jurusan
                kuota = 3,
                lokasi = "Medan",
                mode = ModeKerja.ONLINE,
                jenis = JenisLowongan.UNPAID,
                tglMulai = tglMulai,
                tglSelesai = tglSelesai,
                batasDaftar = batasDaftar,
                pembimbingLapangan = "Rudi Hartono",
                wajibKtm = true
            )
        )
    }

    // Buat satu akun di tabel users dan kembalikan id-nya.
    // Password di-hash dulu, yang disimpan bukan password asli.
    private suspend fun buatUser(db: AppDatabase, role: Role, loginId: String): Long {
        return db.userDao().insert(
            UserEntity(
                role = role,
                loginId = loginId,
                passwordHash = PasswordHasher.hash(DEFAULT_PASSWORD)
            )
        )
    }
}