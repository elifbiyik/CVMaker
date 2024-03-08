package com.eb.cvmaker.Repository

import com.eb.cvmaker.dB.BaseDao
import com.eb.cvmaker.exception
import java.lang.Exception
import javax.inject.Inject

/*

interface BaseRepository<T> {
    suspend fun saveData(userData: T): Boolean
    suspend fun deleteData(): Boolean
    suspend fun getData(): List<T>
}
*/

open class BaseRepository<T> @Inject constructor(private val dao: BaseDao<T>) {
    open suspend fun saveData(userData: T): Boolean {
        var isSuccessful = false
        try {
            dao.insert(userData)
            isSuccessful = true
        } catch (e: Exception) {
            exception(e, "$userData Repository")
            isSuccessful = false
        }
        return isSuccessful
    }

    open suspend fun deleteData(userData: T): Boolean {
        var isSuccessful = false
        try {
            dao.delete(userData)
            isSuccessful = true
        } catch (e: Exception) {
            exception(e, "Repository")
            isSuccessful = false
        }
        return isSuccessful
    }

    open suspend fun updateData(userData: T): Boolean {
        var isSuccessful = false
        try {
            dao.update(userData)
            isSuccessful = true
        } catch (e: Exception) {
            exception(e, "$userData Repository")
            isSuccessful = false
        }
        return isSuccessful
    }

/*    open suspend fun getData(): List<T> {
        return dao.getAllInfo(TableNames.LANGUAGE)
    }*/

}

