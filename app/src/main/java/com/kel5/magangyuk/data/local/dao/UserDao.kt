package com.kel5.magangyuk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kel5.magangyuk.data.local.entity.Role
import com.kel5.magangyuk.data.local.entity.UserEntity

@Dao
interface UserDao {
    // Tambah akun baru, mengembalikan id yang dibuat Room.
    // Kalau loginId sudah ada, Room melempar error (karena index unik).
    @Insert
    suspend fun insert(user: UserEntity): Long

    // Dipakai saat login: cari akun berdasarkan loginId dan role.
    // Role ikut dicek karena tiap role punya halaman login sendiri.
    @Query("SELECT * FROM users WHERE loginId = :loginId AND role = :role LIMIT 1")
    suspend fun findByLoginIdAndRole(loginId: String, role: Role): UserEntity?

    // Dipakai untuk memulihkan sesi login dari id yang tersimpan
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun findById(id: Long): UserEntity?

    // Dipakai seed: kalau 0, berarti database masih kosong
    @Query("SELECT COUNT(*) FROM users")
    suspend fun count(): Int
}
