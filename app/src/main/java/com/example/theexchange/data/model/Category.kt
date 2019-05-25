package com.example.theexchange.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idcountry")
     var id: Int,
    @SerializedName("name")
     var name: String,
    @SerializedName("background_image")
     var background_image: String,
    @SerializedName("description")
     var description: String
)