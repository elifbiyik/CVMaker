package com.eb.cvmaker.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Abilities")
data class Abilities(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "abilitiesName") var abilitiesName: String ?= null,

    @ColumnInfo(name = "level") var level: String ?= null
)
