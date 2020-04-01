package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "SubstanceInMedis",
    foreignKeys = [
        ForeignKey(
            entity = Medis::class,
            parentColumns = ["Uid"],
            childColumns = ["MediUid"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Substances::class,
            parentColumns = ["Uid"],
            childColumns = ["SubstanceUid"],
            onDelete = CASCADE
        )
    ],
    primaryKeys = ["MediUid", "SubstanceUid"])
data class SubstanceInMedis(

    @NonNull
    @ColumnInfo(name = "MediUid")
    val MediUid: Int,

    @ColumnInfo(name = "SubstanceUid")
    @NonNull val SubstanceUid: Int,

    @ColumnInfo(name = "Domain") var Domain: String?,
    @ColumnInfo(name = "Effect") var Effect: String?,
    @ColumnInfo(name = "InCompetition") var InCompetition: Int?,
    @ColumnInfo(name = "OutCompetition") var OutCompetition: Int?,
    @ColumnInfo(name = "Warning") var Warning: Int?
)