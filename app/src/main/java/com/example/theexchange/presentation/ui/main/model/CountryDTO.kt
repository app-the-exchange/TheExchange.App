package com.example.theexchange.presentation.ui.main.model

import com.google.gson.annotations.SerializedName

data class CountryDTO (
    @SerializedName("idcountry")var id: Int,
    @SerializedName("name")var name: String,
    @SerializedName("short_description") var short_description: String,
    @SerializedName("flag_image")var flag_image: String
)