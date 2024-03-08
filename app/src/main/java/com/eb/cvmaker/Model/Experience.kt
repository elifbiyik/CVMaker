package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Experience")
data class Experience(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "companyName", collate = ColumnInfo.NOCASE) var companyName: String ?= null,

    @ColumnInfo(name = "positionName", collate = ColumnInfo.NOCASE) var positionName: String ?= null,

    @ColumnInfo(name = "startDate", collate = ColumnInfo.NOCASE) var startDate: String ?= null,

    @ColumnInfo(name = "finishDate", collate = ColumnInfo.NOCASE) var finishDate: String ?= null,

    @ColumnInfo(name = "infoAboutJob", collate = ColumnInfo.NOCASE) var infoAboutJob: String ?= null

    )
