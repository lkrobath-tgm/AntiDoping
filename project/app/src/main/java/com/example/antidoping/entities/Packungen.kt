package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Packungen")
data class Packungen(
    @ForeignKey(entity = Medis::class,parentColumns = arrayOf("Uid"), childColumns = arrayOf("MediUid"), onDelete = ForeignKey.CASCADE)
    @NonNull @PrimaryKey val MediUid:Int,
    @NonNull@PrimaryKey val PZN:Int,
    @ColumnInfo(name = "Frei") var Frei: Int
)