package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Education")
data class Education(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "schoolName", collate = ColumnInfo.NOCASE) var schoolName: String ?= null,

    @ColumnInfo(name = "departmentName", collate = ColumnInfo.NOCASE) var departmentName: String ?= null,

    @ColumnInfo(name = "startDate", collate = ColumnInfo.NOCASE) var startDate: String ?= null,

    @ColumnInfo(name = "finishDate", collate = ColumnInfo.NOCASE) var finishDate: String ?= null,

    @ColumnInfo(name = "gano", collate = ColumnInfo.NOCASE) var gano: String ?= null

)
