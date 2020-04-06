package com.example.antidoping.entities

import androidx.room.*

class JoinMedisSubstanceData {
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

    fun getDescription():String?{
        return ""
    }

}