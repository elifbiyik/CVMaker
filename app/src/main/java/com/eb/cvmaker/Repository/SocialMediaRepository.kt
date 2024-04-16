package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.SocialMedia
import com.eb.cvmaker.dB.SocialMediaDao
import javax.inject.Inject

class SocialMediaRepository @Inject constructor(private val dao: SocialMediaDao) :
    BaseRepository<SocialMedia>(dao) {

    suspend fun getUserSocialMediaInfo(): List<SocialMedia> {
        return dao.getAllInfo()
    }
}