package com.kel5.magangyuk.util

import java.security.MessageDigest
import java.security.SecureRandom

// Object = satu-satunya instance, jadi bisa dipanggil langsung: PasswordHasher.hash("abc")
object PasswordHasher {

    // Buat hash baru. Hasil berformat "salt:hash", dan inilah yang disimpan di users.passwordHash
    fun hash(password: String): String {
        val saltBytes = ByteArray(16)
        SecureRandom().nextBytes(saltBytes) // salt acak, beda tiap panggilan
        val salt = saltBytes.toHex()
        return "$salt:${sha256(salt + password)}"
    }

    // Cek password yang diketik user dengan hash tersimpan (dipakai saat login)
    fun verify(password: String, stored: String): Boolean {
        val parts = stored.split(":")
        if (parts.size != 2) return false
        val (salt, expectedHash) = parts
        return sha256(salt + password) == expectedHash
    }

    private fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.toHex()
    }

    // Ubah deretan byte jadi teks heksadesimal, misalnya "a3f09c..."
    private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }
}