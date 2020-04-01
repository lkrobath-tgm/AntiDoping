package com.example.antidoping.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "Takings")
data class Takings(
    @NonNull
    @PrimaryKey var Uid:String,
    @ColumnInfo(name = "Name") var Name: String?,
    @ColumnInfo(name = "Desc") var Desc: String?
)