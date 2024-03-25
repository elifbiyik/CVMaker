package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SocialMedia")
data class SocialMedia(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "linkledin") var linkedIn: String ?= null,

    @ColumnInfo(name = "github") var github: String ?= null,

    @ColumnInfo(name = "webSite") var webSite: String ?= null,


    )
