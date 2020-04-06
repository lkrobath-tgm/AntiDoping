package com.example.antidoping.epoxy


import com.airbnb.epoxy.EpoxyController
import com.example.antidoping.R
import com.example.antidoping.entities.JoinMedisSubstanceData


class SingleItemController :EpoxyController(){
    var listMedis: List<JoinMedisSubstanceData> = arrayListOf()

    fun initJoinList(result:List<JoinMedisSubstanceData>){
        listMedis = arrayListOf()
        listMedis = result
    }

    override fun buildModels() {
        var i:Long =0

        listMedis.forEach {item ->
            if(item.getMedisName().equals("")){
                SingleItemModel_()
                    .id(i++)
                    .mediOrSubstanceImage(R.drawable.ic_medicine)
                    .title(item.getName())
                    .addTo(this)
            }else{
                SingleItemModel_()
                    .id(i++)
                    .mediOrSubstanceImage(R.drawable.ic_substance)
                    .title(item.getName())
                    .addTo(this)
            }
        }
    }
}