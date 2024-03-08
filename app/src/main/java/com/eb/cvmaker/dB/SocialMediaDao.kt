package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Query
import com.eb.cvmaker.Model.SocialMedia

@Dao
interface SocialMediaDao : BaseDao<SocialMedia> {

    @Query("SELECT *FROM SocialMedia")
    suspend fun getAllInfo(): List<SocialMedia>

}