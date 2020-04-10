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

    override fun buildModels() {


        listMedis.forEach {item ->
            if(item.getMedisName().equals("")){
                SingleItemModel_(item)
                    .id(index++)
                    //.mediOrSubstanceImage(R.drawable.ic_medicine)
                    //.title(item.getName())
                    .addTo(this)
            }else{
                SingleItemModel_(item)
                    .id(index++)
                    //.image(R.drawable.ic_substance)
                    //.title(item.getName())
                    .addTo(this)
            }

        }


    }
}