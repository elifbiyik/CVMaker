package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eb.cvmaker.Model.Certificates

@Dao
interface CertificatesDao : BaseDao<Certificates>{

/*
    @Insert
    override suspend fun insert (info : Certificates)

    @Delete
    override suspend fun delete (info : Certificates)
*/

    @Query("SELECT *FROM Certificates")
     suspend fun getAllInfo () : List<Certificates>


}