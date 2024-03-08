package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.UserInfo


@Dao
interface UserInfoDao : BaseDao<UserInfo> {

    @Query("SELECT * FROM Education")
    suspend fun getAll(): List<Education>
}
