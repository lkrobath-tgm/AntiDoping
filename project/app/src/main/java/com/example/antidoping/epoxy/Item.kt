package com.example.antidoping.epoxy

import androidx.annotation.StringRes

data class Item (
    val mediOrSubstanceImage:Int=-1,
    val title:String="",
    @StringRes val description:Int=-1,
    val inCompImage:Int=-1,
    val outCompImage:Int=-1
)