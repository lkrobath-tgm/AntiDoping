package com.example.antidoping.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.antidoping.R
import com.example.antidoping.entities.JoinMedisSubstanceData
import kotlinx.android.synthetic.main.single_item_medicine.view.*

@EpoxyModelClass(layout = R.layout.single_item_medicine)
abstract class SingleItemModel (@EpoxyAttribute var item: JoinMedisSubstanceData) : EpoxyModelWithHolder<SingleItemModel.ListHolder>(){

    override fun bind(holder: ListHolder) {
        holder.imageView.setImageResource(R.drawable.ic_medicine)
        holder.titleView.text = item.getName()
    }

    inner class ListHolder : KotlinHolder(){
        val imageView by bind<ImageView>(R.id.image)
        val titleView by bind<TextView>(R.id.title)
        val typeView by bind<TextView>(R.id.type)
    }
}