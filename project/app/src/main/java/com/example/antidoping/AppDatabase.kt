package com.example.antidoping

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.antidoping.entities.Substances
import com.example.antidoping.entities.Takings

@Database(entities = arrayOf(Takings::class,Substances::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNadaDAO(): NadaDAO
}