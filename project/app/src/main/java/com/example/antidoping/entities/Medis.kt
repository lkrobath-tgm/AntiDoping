package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "Medis")
data class Medis(
    @ForeignKey(entity = Takings::class, parentColumns = arrayOf("Uid"), childColumns = arrayOf("Uid"), onDelete = CASCADE)
    @NonNull val Uid: Int,

    @ColumnInfo(name = "Name") var Name: String?,
    @ColumnInfo(name = "TakingUid") var TakingUid: String?,
    @ColumnInfo(name = "AG") var AG: String?,
    @ColumnInfo(name = "EW") var EW: String?,
    @ColumnInfo(name = "NW") var NW: String?,
    @ColumnInfo(name = "InCompetition") var InCompetition: Int?,
    @ColumnInfo(name = "OutCompetition") var OutCompetition: Int?,
    @ColumnInfo(name = "Warning") var Warning: Int?
)