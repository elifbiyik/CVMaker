package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.Model.Education
import com.eb.cvmaker.dB.CertificatesDao
import com.eb.cvmaker.dB.EducationDao
import javax.inject.Inject

class CertificatesAddRepository @Inject constructor(private val dao: CertificatesDao) :
    BaseRepository<Certificates>(dao) {

    suspend fun getUserCertificatesInfo(): List<Certificates> {
        return dao.getAllInfo()
    }
}

