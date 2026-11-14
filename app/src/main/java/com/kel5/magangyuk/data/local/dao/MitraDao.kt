package com.kel5.magangyuk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kel5.magangyuk.data.local.entity.MitraEntity

@Dao
interface MitraDao {
    // Dipakai seed untuk mengisi data awal mitra
    @Insert
    suspend fun insert(mitra: MitraEntity)

    // Ambil profil perusahaan berdasarkan id akunnya (users.id)
    @Query("SELECT * FROM mitra WHERE userId = :userId LIMIT 1")
    suspend fun findByUserId(userId: Long): MitraEntity?

    // Dipakai saat simpan Profil Perusahaan. Field yang boleh diubah user
    // (alamat, deskripsi, telepon, email, website) dijaga di Repository/ViewModel.
    @Update
    suspend fun update(mitra: MitraEntity)
}