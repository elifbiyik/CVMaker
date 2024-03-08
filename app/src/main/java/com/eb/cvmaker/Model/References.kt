package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "References")
data class References(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE) var name: String ?= null,

    @ColumnInfo(name = "surname", collate = ColumnInfo.NOCASE) var surname: String ?= null,

    @ColumnInfo(name = "positionName", collate = ColumnInfo.NOCASE) var positionName: String ?= null,

    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) var email: String ?= null,

    @ColumnInfo(name = "phone", collate = ColumnInfo.NOCASE) var phone: String ?= null

    )
