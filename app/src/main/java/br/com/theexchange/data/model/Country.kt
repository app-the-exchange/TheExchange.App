package br.com.theexchange.data.model

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("idcountry") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("short_description") var short_description: String,
    @SerializedName("flag_image") var flag_image: String,
    @SerializedName("banner_image") var banner_image: String,
    @SerializedName("category") var categories: ArrayList<Category>?
)