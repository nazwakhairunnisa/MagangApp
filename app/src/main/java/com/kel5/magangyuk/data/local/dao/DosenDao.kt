package com.kel5.magangyuk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kel5.magangyuk.data.local.entity.DosenEntity

@Dao
interface DosenDao {
    // Dipakai seed untuk mengisi data awal dosen
    @Insert
    suspend fun insert(dosen: DosenEntity)

    // Ambil data dosen berdasarkan id akunnya (users.id)
    @Query("SELECT * FROM dosen WHERE userId = :userId LIMIT 1")
    suspend fun findByUserId(userId: Long): DosenEntity?

    // Dipakai saat simpan Profil. Yang boleh diubah user hanya telepon dan email;
    // pembatasan itu dijaga di Repository/ViewModel.
    @Update
    suspend fun update(dosen: DosenEntity)
}