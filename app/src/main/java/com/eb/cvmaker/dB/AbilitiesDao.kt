package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Abilities

@Dao
interface AbilitiesDao : BaseDao<Abilities> {

    @Query("SELECT *FROM Abilities")
     suspend fun getAllInfo () : List<Abilities>

}