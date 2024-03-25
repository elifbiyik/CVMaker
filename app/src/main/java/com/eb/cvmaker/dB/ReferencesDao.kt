package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.References

@Dao
interface ReferencesDao : BaseDao<References> {

    @Query("SELECT *FROM `References`")
     suspend fun getAllInfo(): List<References>
}