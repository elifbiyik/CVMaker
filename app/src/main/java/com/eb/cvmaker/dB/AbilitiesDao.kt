package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Abilities

@Dao
interface AbilitiesDao : BaseDao<Abilities> {

 /*   @Insert
    override suspend fun insert (info : Abilities)

  //  @Query("DELETE FROM Abilities WHERE id = 1")
    @Delete
    override suspend fun delete(info : Abilities)
*/
    @Query("SELECT *FROM Abilities")
     suspend fun getAllInfo () : List<Abilities>

}