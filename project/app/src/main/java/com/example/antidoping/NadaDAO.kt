package com.example.antidoping

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Query
import com.example.antidoping.entities.Medis
import com.example.antidoping.entities.Substances
import com.example.antidoping.entities.Takings
import org.jetbrains.annotations.NotNull

@Dao
interface NadaDAO {
    @Query("SELECT * FROM Takings")
    fun getAllTakings():List<Takings>

    @Query("SELECT * FROM Takings where Uid LIKE :uid")
    fun getSpecUid(uid:String):Takings

    @Query("SELECT * FROM Medis where Name LIKE :name")
    fun getMedisByName(name:String):Medis

    @Query("SELECT * FROM Substances where Name LIKE :name")
    fun getSubstancesByName(name:String):Substances

}