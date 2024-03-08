package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Abilities
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.dB.AbilitiesDao
import javax.inject.Inject

class AbilitiesRepository @Inject constructor(private val dao: AbilitiesDao) :
    BaseRepository<Abilities>(dao) {

    suspend fun getUserAbilitiesInfo(): List<Abilities> {
        return dao.getAllInfo()
    }


}