package com.eb.cvmaker.dB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import javax.inject.Named

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userData: T)

    @Delete
    suspend fun delete(userData: T)

    @Update
    suspend fun update(userData: T)

/*
    @Query("SELECT *FROM :t")
    suspend fun getAllInfo(t : String): List<T>
*/



}


/*
  @Query("SELECT *FROM :t")
    suspend fun getAllInfo(t : String): List<T>

    <table or subquery> expected, got ':t'
    hatası veriyor.

    Bundan dolayı TableNameProvider interface'i oluşturulup tablo adı alınıyor.
 */