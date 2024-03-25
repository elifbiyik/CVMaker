package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Query
import com.eb.cvmaker.Model.Experience

@Dao
interface ExperienceDao : BaseDao<Experience> {

    @Query("SELECT *FROM Experience")
     suspend fun getAllInfo(): List<Experience>

}