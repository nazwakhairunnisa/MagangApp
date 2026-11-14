package com.kel5.magangyuk.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kel5.magangyuk.data.local.dao.DosenDao
import com.kel5.magangyuk.data.local.dao.LowonganDao
import com.kel5.magangyuk.data.local.dao.MahasiswaDao
import com.kel5.magangyuk.data.local.dao.MitraDao
import com.kel5.magangyuk.data.local.dao.UserDao
import com.kel5.magangyuk.data.local.entity.BookmarkEntity
import com.kel5.magangyuk.data.local.entity.DosenEntity
import com.kel5.magangyuk.data.local.entity.LamaranEntity
import com.kel5.magangyuk.data.local.entity.LaporanEntity
import com.kel5.magangyuk.data.local.entity.LogbookEntity
import com.kel5.magangyuk.data.local.entity.LowonganEntity
import com.kel5.magangyuk.data.local.entity.MagangEntity
import com.kel5.magangyuk.data.local.entity.MahasiswaEntity
import com.kel5.magangyuk.data.local.entity.MitraEntity
import com.kel5.magangyuk.data.local.entity.NotifikasiEntity
import com.kel5.magangyuk.data.local.entity.PenilaianDosenEntity
import com.kel5.magangyuk.data.local.entity.PenilaianMitraEntity
import com.kel5.magangyuk.data.local.entity.PresensiEntity
import com.kel5.magangyuk.data.local.entity.UserEntity

// Semua tabel (14 Entity) didaftarkan di sini. Room membuat tabelnya dari daftar ini.
// version = 1: naikkan kalau struktur tabel berubah (lihat catatan di bawah kode).
@Database(
    entities = [
        UserEntity::class,
        MahasiswaEntity::class,
        MitraEntity::class,
        DosenEntity::class,
        LowonganEntity::class,
        BookmarkEntity::class,
        LamaranEntity::class,
        MagangEntity::class,
        PresensiEntity::class,
        LogbookEntity::class,
        LaporanEntity::class,
        PenilaianMitraEntity::class,
        PenilaianDosenEntity::class,
        NotifikasiEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Room menulis isi fungsi-fungsi ini otomatis
    abstract fun userDao(): UserDao
    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun mitraDao(): MitraDao
    abstract fun dosenDao(): DosenDao
    abstract fun lowonganDao(): LowonganDao

    companion object {
        // Satu-satunya objek database di seluruh app (singleton)
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // Kalau sudah ada, pakai yang ada. Kalau belum, buat sekali saja.
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "magangyuk.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}