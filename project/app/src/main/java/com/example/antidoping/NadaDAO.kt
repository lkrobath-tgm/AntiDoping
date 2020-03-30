package com.example.antidoping

import androidx.room.Dao
import androidx.room.Query
import com.example.antidoping.entities.Medis
import com.example.antidoping.entities.Substances
import com.example.antidoping.entities.Takings
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface NadaDAO {
    @Query("SELECT * FROM Takings")
    fun getAllTakings(): Single<List<Takings>>

    @Query("SELECT * FROM Takings where Uid LIKE :uid")
    fun getSpecUid(uid: String): Takings

    @Query("SELECT * FROM Medis where Name LIKE :name")
    fun getMedisByName(name: String): Medis


    @Query(
        """SELECT * FROM Substances
            WHERE Name LIKE '%'||:name||'%'
          """
    )
    fun getSubstancesByName(name: String): Observable<List<Substances>>

    /*@Query("SELECT * FROM Substances LEFT OUTER JOIN Medis WHERE Name LIKE '%'||:name||'%' ")
    fun getEverthingByName(name:String): Observable<List<>>*/
}