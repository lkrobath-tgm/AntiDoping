package com.example.antidoping.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "Medis",
    primaryKeys = ["Uid"],
    foreignKeys = [
        ForeignKey(entity = Takings::class, parentColumns = arrayOf("Uid"), childColumns = arrayOf("TakingUid"))],
    indices = [Index("TakingUid")])
data class Medis(
    @ColumnInfo(name = "Uid")
    @NonNull val Uid: Int,

    @ColumnInfo(name = "Name") var Name: String?,

    @ColumnInfo(name = "TakingUid")
    var TakingUid: String?,
    @ColumnInfo(name = "AG") var AG: String?,
    @ColumnInfo(name = "EW") var EW: String?,
    @ColumnInfo(name = "NW") var NW: String?,
    @ColumnInfo(name = "InCompetition") var InCompetition: Int?,
    @ColumnInfo(name = "OutCompetition") var OutCompetition: Int?,
    @ColumnInfo(name = "Warning") var Warning: Int?
):Parcelable