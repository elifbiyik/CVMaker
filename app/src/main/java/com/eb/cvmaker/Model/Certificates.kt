package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Certificates")
data class Certificates(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "certificateProjectOrAwardName", collate = ColumnInfo.NOCASE) var certificateProjectOrAwardName: String ?= null,

    @ColumnInfo(name = "educationPlace", collate = ColumnInfo.NOCASE) var educationPlace: String ?= null,

    @ColumnInfo(name = "startDate", collate = ColumnInfo.NOCASE) var startDate: String ?= null,

    @ColumnInfo(name = "finishDate", collate = ColumnInfo.NOCASE) var finishDate: String ?= null,

    @ColumnInfo(name = "aboutCertificate", collate = ColumnInfo.NOCASE) var aboutCertificate: String ?= null,
    )
