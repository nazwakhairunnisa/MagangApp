package com.kel5.magangyuk

import com.kel5.magangyuk.data.session.SessionManager
import android.app.Application
import com.kel5.magangyuk.data.local.AppDatabase
import com.kel5.magangyuk.data.local.DatabaseSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Dijalankan Android satu kali setiap app dimulai, sebelum layar pertama muncul
class MagangYukApp : Application() {

    // lazy = database baru dibuat saat pertama kali dipakai
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    // Pencatat sesi login (siapa yang sedang login), dibuat sekali untuk seluruh app
    val sessionManager: SessionManager by lazy { SessionManager(this) }

    override fun onCreate() {
        super.onCreate()

        // Isi data awal di thread IO. seedIfEmpty melewati proses kalau akun sudah ada.
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseSeeder.seedIfEmpty(database)
        }
    }
}