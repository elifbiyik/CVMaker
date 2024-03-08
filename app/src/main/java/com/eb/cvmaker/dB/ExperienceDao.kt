package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.Model.UserInfo

@Dao
interface ExperienceDao : BaseDao<Experience> {

/*
    @Insert
    override suspend fun insert(info: Experience)

    //@Query("DELETE FROM Experience WHERE id = 1")
    @Delete
    override suspend fun delete(info: Experience)
*/

    @Query("SELECT *FROM Experience")
     suspend fun getAllInfo(): List<Experience>


}