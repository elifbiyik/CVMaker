package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eb.cvmaker.Model.Languages

@Dao
interface LanguageDao : BaseDao<Languages> {

/*
    @Insert
    override suspend fun insert(info: Languages)
*/

/*
   // @Query("DELETE FROM Languages WHERE id = 1")
    @Delete
    override suspend fun delete(info: Languages)
*/
/*

    @Update
    override suspend fun update(info: Languages)
*/

    @Query("SELECT *FROM Languages")
    suspend fun getAllInfo(): List<Languages>




}