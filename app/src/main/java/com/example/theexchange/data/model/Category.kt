package com.example.theexchange.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idcountry")
    private var id: Int,
    @SerializedName("name")
    private var name: String,
    @SerializedName("background_image")
    private var background_image: String,
    @SerializedName("description")
    private var description: String
) {
}