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
        if(getType().equals("Medi")){
            return medis?.Name
        }else{
            return substance?.Name
        }
    }

    fun getType():String?{
        if(substance == null){
            return "Medi"
        }else{
            return "Substance"
        }
    }

    fun getInComp():Int?{
        return medis?.InCompetition
    }

    fun getOutComp():Int?{
        return medis?.OutCompetition
    }

    fun getId():Int?{
        if(getType().equals("Medi")){
            return medis?.Uid
        }else{
            return substance?.Uid
        }
    }

}