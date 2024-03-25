package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eb.cvmaker.Model.Languages

@Dao
interface LanguageDao : BaseDao<Languages> {

    @Query("SELECT *FROM Languages")
    suspend fun getAllInfo(): List<Languages>

}