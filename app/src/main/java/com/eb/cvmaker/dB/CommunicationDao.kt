package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Query
import com.eb.cvmaker.Model.Communication

@Dao
interface CommunicationDao : BaseDao<Communication> {

    @Query("SELECT *FROM Communication")
     suspend fun getAllInfo () : List<Communication>

}