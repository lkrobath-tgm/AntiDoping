package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "SubstanceInMedis",primaryKeys = arrayOf("SubstanceUid","MediUid"))
data class SubstanceInMedis(
    @ForeignKey(entity = Medis::class,parentColumns = arrayOf("Uid"), childColumns = arrayOf("MediUid"), onDelete = CASCADE)
    @NonNull val MediUid:Int,
    @ForeignKey(entity = Substances::class, parentColumns = arrayOf("Uid"), childColumns = arrayOf("SubstanceUid"), onDelete = CASCADE)
    @NonNull val SubstanceUid:Int,
    @ColumnInfo(name = "Name") var Name: String?,
    @ColumnInfo(name = "Domain") var Domain: String?,
    @ColumnInfo(name = "Effect") var Effect: String?,
    @ColumnInfo(name = "InCompetition") var InCompetition: String?,
    @ColumnInfo(name = "OutCompetition") var OutCompetition: String?,
    @ColumnInfo(name = "Warning") var Warning: String?
)