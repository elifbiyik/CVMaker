package com.eb.cvmaker.Repository

import com.eb.cvmaker.Model.References
import com.eb.cvmaker.dB.ReferencesDao
import javax.inject.Inject

class ReferencesAddRepository @Inject constructor(private val dao: ReferencesDao) :
    BaseRepository<References>(dao) {
}