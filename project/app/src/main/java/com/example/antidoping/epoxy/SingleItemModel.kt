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

@EpoxyModelClass(layout = R.layout.singlemedicine_layout)
abstract class SingleItemModel (@EpoxyAttribute var item: JoinMedisSubstanceData) : EpoxyModelWithHolder<SingleItemModel.ListHolder>(){

    override fun bind(holder: ListHolder) {
        holder.typeView.text = item.getType()
        if(holder.typeView.text.equals("Medi")){
            holder.imageView.setImageResource(R.drawable.ic_medicine)
            when(item.getInComp()){
                0 -> holder.inCompView.setImageResource(R.drawable.ic_in_comp_allowed)
                1 -> holder.inCompView.setImageResource(R.drawable.ic_in_comp_restricted)
                2 -> holder.inCompView.setImageResource(R.drawable.ic_in_comp_forbidden)
            }
            when(item.getOutComp()){
                0 -> holder.outCompView.setImageResource(R.drawable.ic_in_comp_allowed)
                1 -> holder.outCompView.setImageResource(R.drawable.ic_in_comp_restricted)
                2 -> holder.outCompView.setImageResource(R.drawable.ic_in_comp_forbidden)
            }
        }else{
            holder.imageView.setImageResource(R.drawable.ic_substance)
        }

        holder.titleView.text = item.getName()

    }

    inner class ListHolder : KotlinHolder(), View.OnClickListener{
        override fun onClick(v: View?) {

        }

        val imageView by bind<ImageView>(R.id.image)
        val inCompView by bind<ImageView>(R.id.inComp)
        val outCompView by bind<ImageView>(R.id.outComp)
        val titleView by bind<TextView>(R.id.title)
        val typeView by bind<TextView>(R.id.type)

    }
}