package com.example.antidoping.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "Medis",
    primaryKeys = ["Uid"],
    foreignKeys = [
        ForeignKey(
            entity = Takings::class,
            parentColumns = arrayOf("Uid"),
            childColumns = arrayOf("TakingUid")
        )]
)
data class Medis(
    @ColumnInfo(name = "Uid")
    val Uid: Int,
    @NonNull
    @ColumnInfo(name = "Name") var Name: String?,

    @ColumnInfo(name = "TakingUid")
    var TakingUid: String?,
    @ColumnInfo(name = "AG") var AG: String?,
    @ColumnInfo(name = "EW") var EW: String?,
    @ColumnInfo(name = "NW") var NW: String?,
    @ColumnInfo(name = "InCompetition") var InCompetition: Int?,
    @ColumnInfo(name = "OutCompetition") var OutCompetition: Int?,
    @ColumnInfo(name = "Warning") var Warning: Int?
) : Parcelable