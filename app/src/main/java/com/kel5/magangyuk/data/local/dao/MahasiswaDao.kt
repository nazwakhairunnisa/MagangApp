package com.kel5.magangyuk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kel5.magangyuk.data.local.entity.MahasiswaEntity

@Dao
interface MahasiswaDao {
    // Dipakai seed untuk mengisi data awal mahasiswa
    @Insert
    suspend fun insert(mahasiswa: MahasiswaEntity)

    // Ambil data diri mahasiswa berdasarkan id akunnya (users.id)
    @Query("SELECT * FROM mahasiswa WHERE userId = :userId LIMIT 1")
    suspend fun findByUserId(userId: Long): MahasiswaEntity?

    // Dipakai saat simpan Profil. Yang boleh diubah user hanya telepon dan email;
    // pembatasan itu dijaga di Repository/ViewModel, bukan di sini.
    @Update
    suspend fun update(mahasiswa: MahasiswaEntity)
}