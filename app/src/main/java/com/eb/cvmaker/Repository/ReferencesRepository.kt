package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.References
import com.eb.cvmaker.dB.ReferencesDao
import javax.inject.Inject

class ReferencesRepository @Inject constructor(private val dao: ReferencesDao) :
    BaseRepository<References>(dao) {

    suspend fun getUserCertificatesInfo(): List<References> {
        return dao.getAllInfo()
    }
}