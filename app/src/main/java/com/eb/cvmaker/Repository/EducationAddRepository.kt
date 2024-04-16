package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.dB.EducationDao
import javax.inject.Inject

class EducationAddRepository @Inject constructor(private val dao: EducationDao) :
    BaseRepository<Education>(dao) {

}