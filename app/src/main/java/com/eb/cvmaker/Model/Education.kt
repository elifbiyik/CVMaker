package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Education")
data class Education(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "schoolName") var schoolName: String ?= null,

    @ColumnInfo(name = "departmentName") var departmentName: String ?= null,

    @ColumnInfo(name = "startDate") var startDate: String ?= null,

    @ColumnInfo(name = "finishDate") var finishDate: String ?= null,

    @ColumnInfo(name = "gano") var gano: String ?= null

)
