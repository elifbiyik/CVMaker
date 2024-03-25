package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Communication
import com.eb.cvmaker.dB.CommunicationDao
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class CommunicationRepository @Inject constructor(
    private val dao: CommunicationDao, private var auth: FirebaseAuth
) :
    BaseRepository<Communication>(dao) {

    suspend fun getUserCommunicationInfo(): List<Communication> {
        return dao.getAllInfo()
    }

}