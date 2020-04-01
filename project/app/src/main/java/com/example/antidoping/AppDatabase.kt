package com.example.antidoping

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.antidoping.entities.*

@Database(
    entities = [Substances::class, Takings::class, Medis::class, Packungen::class, SubstanceInMedis::class], version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNadaDAO(): NadaDAO
}