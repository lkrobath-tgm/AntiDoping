package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Packungen",primaryKeys = arrayOf("MediUid", "PZN"),foreignKeys = [ForeignKey(entity = Medis::class,parentColumns = arrayOf("Uid"), childColumns = arrayOf("MediUid"), onDelete = ForeignKey.CASCADE)])
data class Packungen(
    @NonNull val MediUid:Int,
    @NonNull val PZN:Int,
    @ColumnInfo(name = "Frei") var Frei: Int
)