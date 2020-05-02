package com.example.antidoping.epoxy


import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.example.antidoping.R
import com.example.antidoping.entities.JoinMedisSubstanceData


class SingleItemController :EpoxyController(){
    private var listMedis: List<JoinMedisSubstanceData> = arrayListOf()
    private var index = 0L

    fun initJoinList(result:List<JoinMedisSubstanceData>){
        listMedis = arrayListOf()
        listMedis = result
    }

    fun initHowManyResults() : String{
        return listMedis.size.toString() + " Suchergebnisse"
    }

    override fun buildModels() {
        listMedis.forEach {item ->
            if(item.getMedisName().equals("")){
                SingleItemModel_(item)
                    .id(item.getId())
                    .addTo(this)
            }else{
                SingleItemModel_(item)
                    .id(index++)
                    .addTo(this)
            }
        }
    }

    fun getListMedis():List<JoinMedisSubstanceData>{
        return listMedis
    }
}