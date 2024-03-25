package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Communication")
data class Communication(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "image") var image: String? = null,

    @ColumnInfo(name = "name") var name: String ?= null,

    @ColumnInfo(name = "surname") var surname: String ?= null,

    @ColumnInfo(name = "job") var job: String ?= null,

    @ColumnInfo(name = "email") var email: String ?= null,

    @ColumnInfo(name = "phone") var phone: String ?= null,

    @ColumnInfo(name = "aboutMe") var aboutMe: String ?= null,

    @ColumnInfo(name = "address") var address: String ?= null,

    @ColumnInfo(name = "birtday") var birtday: String? = null,

    @ColumnInfo(name = "gender") var gender: String? = null,

    @ColumnInfo(name = "drivingLicence") var drivingLicence: String? = null,

    @ColumnInfo(name = "military") var military: String? = null,

    )
