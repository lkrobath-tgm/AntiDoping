package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Substances")
data class Substances(
    @NonNull
    @PrimaryKey val Uid: Int,
    @ColumnInfo(name = "Name") var Name: String?
)