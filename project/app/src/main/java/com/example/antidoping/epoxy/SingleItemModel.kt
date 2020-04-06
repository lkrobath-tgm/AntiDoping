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
import kotlinx.android.synthetic.main.single_item_medicine.view.*

@EpoxyModelClass(layout = R.layout.single_item_medicine)
abstract class SingleItemModel :EpoxyModelWithHolder<SingleItemModel.ListHolder>() {

    @EpoxyAttribute
    var id : Long = 0

    @EpoxyAttribute
    @DrawableRes
    var mediOrSubstanceImage : Int = 0

    @EpoxyAttribute
    @DrawableRes
    var inCompImage : Int = 0

    @EpoxyAttribute
    @DrawableRes
    var outCompImage : Int = 0


    @EpoxyAttribute
    var title:String? = ""

    override fun bind(holder: ListHolder) {
        if(mediOrSubstanceImage != null){
            holder.imageView.setImageResource(mediOrSubstanceImage)
        }
        if(title != null){
            holder.titleView.text = title
        }
    }

    inner class ListHolder : EpoxyHolder(){
        lateinit var imageView: ImageView
        lateinit var titleView: TextView
        lateinit var descView:TextView

        override fun bindView(itemView: View) {
            imageView = itemView.image
            titleView = itemView.title
        }
    }
}