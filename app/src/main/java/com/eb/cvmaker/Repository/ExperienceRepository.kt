package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Experience
import com.eb.cvmaker.dB.ExperienceDao
import javax.inject.Inject

class ExperienceRepository @Inject constructor(private val dao: ExperienceDao) : BaseRepository<Experience> (dao){

    suspend fun getUserExperienceInfo(): List<Experience> {
        return dao.getAllInfo()
    }
}
