package com.example.antidoping

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.example.antidoping.entities.Medis
import com.example.antidoping.entities.Substances

class JoinMedisSubstanceData {
    @Embedded var medis:Medis? = null
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

   /* fun getMedisName(): String? {
        return medisName
    }

    fun setMedisName(medisName: String) {
        this.medisName = medisName
    }*/
}