package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SocialMedia")
data class SocialMedia(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "linkledin", collate = ColumnInfo.NOCASE) var linkedIn: String ?= null,
    @ColumnInfo(name = "github", collate = ColumnInfo.NOCASE) var github: String ?= null,
    @ColumnInfo(name = "webSite", collate = ColumnInfo.NOCASE) var webSite: String ?= null,


    )
