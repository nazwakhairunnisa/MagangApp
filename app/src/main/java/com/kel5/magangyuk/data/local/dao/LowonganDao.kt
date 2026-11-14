package com.kel5.magangyuk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kel5.magangyuk.data.local.entity.LowonganEntity

@Dao
interface LowonganDao {

    // Dipakai seed untuk mengisi contoh lowongan. Mengembalikan id yang dibuat Room.
    @Insert
    suspend fun insert(lowongan: LowonganEntity): Long

    // Dipakai untuk mengecek jumlah lowongan di database
    @Query("SELECT COUNT(*) FROM lowongan")
    suspend fun count(): Int
}