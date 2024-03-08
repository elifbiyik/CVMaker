package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Languages")
data class Languages(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "languageName", collate = ColumnInfo.NOCASE) var languageName: String ?= null,

    @ColumnInfo(name = "level", collate = ColumnInfo.NOCASE) var level: String ?= null

    )
