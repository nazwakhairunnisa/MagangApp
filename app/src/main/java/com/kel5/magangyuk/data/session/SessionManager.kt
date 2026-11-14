package com.kel5.magangyuk.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kel5.magangyuk.data.local.entity.Role
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Isi catatan sesi: siapa yang sedang login dan sebagai apa
data class Session(val userId: Long, val role: Role)

// Membuat satu DataStore bernama "session" untuk seluruh app (disimpan di file internal HP)
private val Context.sessionDataStore by preferencesDataStore(name = "session")

class SessionManager(private val context: Context) {

    // Nama "laci" tempat data disimpan
    private object Keys {
        val USER_ID = longPreferencesKey("user_id")
        val ROLE = stringPreferencesKey("role")
    }

    // Aliran data sesi. Nilainya null kalau belum ada yang login.
    val session: Flow<Session?> = context.sessionDataStore.data.map { prefs ->
        val userId = prefs[Keys.USER_ID]
        val roleName = prefs[Keys.ROLE]
        if (userId == null || roleName == null) {
            null
        } else {
            // Kalau teks role tidak dikenali (data rusak), anggap belum login
            val role = Role.entries.firstOrNull { it.name == roleName }
            if (role == null) null else Session(userId, role)
        }
    }

    // Dipanggil saat login berhasil
    suspend fun saveSession(userId: Long, role: Role) {
        context.sessionDataStore.edit { prefs ->
            prefs[Keys.USER_ID] = userId
            prefs[Keys.ROLE] = role.name
        }
    }

    // Dipanggil saat logout (hapus semua catatan sesi)
    suspend fun clearSession() {
        context.sessionDataStore.edit { prefs -> prefs.clear() }
    }
}