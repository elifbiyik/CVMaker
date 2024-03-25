package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Experience")
data class Experience(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "companyName") var companyName: String ?= null,

    @ColumnInfo(name = "positionName") var positionName: String ?= null,

    @ColumnInfo(name = "startDate") var startDate: String ?= null,

    @ColumnInfo(name = "finishDate") var finishDate: String ?= null,

    @ColumnInfo(name = "infoAboutJob") var infoAboutJob: String ?= null

    )
