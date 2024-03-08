package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.dB.CertificatesDao
import javax.inject.Inject

class CertificatesRepository  @Inject constructor(private val dao: CertificatesDao) :
    BaseRepository<Certificates>(dao) {

    suspend fun getUserCertificatesInfo(): List<Certificates> {
        return dao.getAllInfo()
    }

}