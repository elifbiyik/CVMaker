package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Communication")
data class Communication(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE) var name: String ?= null,

    @ColumnInfo(name = "surname", collate = ColumnInfo.NOCASE) var surname: String ?= null,

    @ColumnInfo(name = "job", collate = ColumnInfo.NOCASE) var job: String ?= null,

    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) var email: String ?= null,

    @ColumnInfo(name = "phone", collate = ColumnInfo.NOCASE) var phone: String ?= null,

    @ColumnInfo(name = "aboutMe", collate = ColumnInfo.NOCASE) var aboutMe: String ?= null,

    @ColumnInfo(name = "address", collate = ColumnInfo.NOCASE) var address: String ?= null,

    @ColumnInfo(name = "birtday", collate = ColumnInfo.NOCASE) var birtday: String? = null,

    @ColumnInfo(name = "gender", collate = ColumnInfo.NOCASE) var gender: String? = null,

    @ColumnInfo(name = "drivingLicence", collate = ColumnInfo.NOCASE) var drivingLicence: String? = null,

    @ColumnInfo(name = "military", collate = ColumnInfo.NOCASE) var military: String? = null,

    )
