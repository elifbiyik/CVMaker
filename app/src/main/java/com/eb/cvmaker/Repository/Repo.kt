package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.Model.UserInfo
import com.eb.cvmaker.dB.EducationDao

import javax.inject.Inject

class Repo @Inject constructor(private val dao: EducationDao) :
    BaseRepository<Education>(dao) {

    suspend fun getAll(): List<Education> {
        return dao.getAllInfo()
    }
}