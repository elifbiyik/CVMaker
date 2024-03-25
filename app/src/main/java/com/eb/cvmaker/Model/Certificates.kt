package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Certificates")
data class Certificates(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "certificateProjectOrAwardName") var certificateProjectOrAwardName: String ?= null,

    @ColumnInfo(name = "educationPlace") var educationPlace: String ?= null,

    @ColumnInfo(name = "startDate") var startDate: String ?= null,

    @ColumnInfo(name = "finishDate") var finishDate: String ?= null,

    @ColumnInfo(name = "aboutCertificate") var aboutCertificate: String ?= null,
    )
