package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Education

@Dao
interface EducationDao : BaseDao<Education> {

/*
    @Insert
    override suspend fun insert(info: Education)

    //   @Query("DELETE FROM Education WHERE id = 1")
    @Delete
    override suspend fun delete(info: Education)
*/

    @Query("SELECT *FROM Education")
     suspend fun getAllInfo(): List<Education>

}
