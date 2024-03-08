package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.Languages
import com.eb.cvmaker.dB.LanguageDao
import javax.inject.Inject

class LanguagesRepository @Inject constructor(private val dao: LanguageDao) : BaseRepository<Languages>(dao) {

    suspend fun getUserLanguagesInfo(): List<Languages> {
        return dao.getAllInfo()
    }

}