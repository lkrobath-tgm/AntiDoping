package com.example.antidoping.dao

import android.graphics.Paint
import androidx.room.Dao
import androidx.room.Query
import com.example.antidoping.entities.JoinMedisSubstanceData
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

    @Query(
        """SELECT * FROM Substances
            WHERE Name LIKE '%'||:name||'%'
          """
    )
    fun getSubstancesByName(name: String): Observable<List<JoinMedisSubstanceData>>

    @Query(
        """SELECT * FROM Medis
            WHERE Name LIKE '%'||:name||'%'
          """
    )
    fun getMedisByName(name: String): Observable<List<JoinMedisSubstanceData>>

    /*@Query("SELECT * FROM Substances LEFT OUTER JOIN Medis WHERE Substances.Name LIKE '%'||:name||'%' OR Medis.Name LIKE '%'||:name||'%'")
    fun getSubstancesAndMedisByName(name:String): Observable<List<JoinMedisSubstanceData>>*/
    //Data class mit Room annotations
    @Query("SELECT Medis.Name, Medis.Uid, Medis.InCompetition, Medis.OutCompetition FROM Medis WHERE Medis.Name LIKE '%'||:name||'%'")
    fun getMedisInJoin(name: String):Observable<List<JoinMedisSubstanceData>>

    @Query("SELECT * FROM Substances WHERE Substances.Name LIKE '%'||:name||'%'")
    fun getSubstancesInJoin(name: String):Observable<List<JoinMedisSubstanceData>>


    @Query("SELECT Substances.Name, Substances.Uid FROM Substances WHERE Substances.Name LIKE '%'||:name||'%' UNION SELECT Medis.Name, Medis.Uid FROM Medis WHERE Medis.Name LIKE '%'||:name||'%'")
    fun getMedisAndSubstances(name: String):Observable<List<JoinMedisSubstanceData>>


}