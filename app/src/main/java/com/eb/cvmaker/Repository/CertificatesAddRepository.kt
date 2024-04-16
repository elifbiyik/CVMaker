package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Certificates
import com.eb.cvmaker.dB.CertificatesDao
import javax.inject.Inject

class CertificatesAddRepository @Inject constructor(private val dao: CertificatesDao) :
    BaseRepository<Certificates>(dao) {

}

