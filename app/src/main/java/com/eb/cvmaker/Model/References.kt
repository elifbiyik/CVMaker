package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "References")
data class References(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "name") var name: String ?= null,

    @ColumnInfo(name = "surname") var surname: String ?= null,

    @ColumnInfo(name = "positionName") var positionName: String ?= null,

    @ColumnInfo(name = "email") var email: String ?= null,

    @ColumnInfo(name = "phone") var phone: String ?= null

    )
