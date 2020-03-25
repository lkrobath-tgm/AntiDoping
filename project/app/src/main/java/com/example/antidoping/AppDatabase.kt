package com.example.antidoping

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.antidoping.entities.*

@Database(entities = arrayOf(Takings::class,Substances::class,Medis::class,Packungen::class,SubstanceInMedis::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNadaDAO(): NadaDAO
}