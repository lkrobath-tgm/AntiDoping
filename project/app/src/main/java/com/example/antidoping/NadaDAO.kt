package com.example.antidoping

import androidx.room.Dao
import androidx.room.Query
import com.example.antidoping.entities.Takings

@Dao
interface NadaDAO {
    @Query("SELECT * FROM Takings")
    fun getAllTakings():List<Takings>

    @Query("SELECT * FROM Takings where Uid LIKE :uid")
    fun getSpecUid(uid:String):Takings

}