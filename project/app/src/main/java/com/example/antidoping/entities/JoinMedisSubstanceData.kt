package com.example.antidoping.entities

import androidx.room.*

/*@Entity(
    tableName = "JoinMedisSubstanceData",
    indices = [
        Index(value = ["Medis", "Substances"])
    ],
    foreignKeys = [
        ForeignKey(entity = Medis::class, parentColumns = ["Name"], childColumns = ["substanceName"]),
        ForeignKey(entity = Substances::class, parentColumns = ["Name"], childColumns = ["medisName"])
    ]
)*/
class JoinMedisSubstanceData {
    /*@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_ID")
    var id: Long = 0

    @ColumnInfo(name = "substanceName")
    var substanceName:String? = null

    @ColumnInfo(name = "medisName")
    var medisName:String? = null

    fun getName():String{
        return medisName+substanceName
    }*/








    @Embedded
    var medis:Medis? = null

    @Relation(
        parentColumn = "Name",
        entityColumn = "Name"
    )
    var substance:Substances? = null

    //@Embedded private var medisName: String? = null
    fun getMedisName(): String? {
        return medis?.Name
    }

    fun getName():String?{
        return medis?.Name+substance?.Name
    }

}