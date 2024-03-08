package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.References

@Dao
interface ReferencesDao : BaseDao<References> {

/*

    @Insert
    override suspend fun insert(info: References)

   // @Query("DELETE FROM `References` WHERE id = 1")
    @Delete
    override suspend fun delete(info: References)
*/

    @Query("SELECT *FROM `References`")
     suspend fun getAllInfo(): List<References>
}