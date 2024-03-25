package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Education

@Dao
interface EducationDao : BaseDao<Education> {

    @Query("SELECT *FROM Education")
     suspend fun getAllInfo(): List<Education>

}
