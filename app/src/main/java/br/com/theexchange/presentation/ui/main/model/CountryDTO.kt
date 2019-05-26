package br.com.theexchange.presentation.ui.main.model

import br.com.theexchange.data.model.Category
import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("idcountry") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("short_description") var short_description: String,
    @SerializedName("flag_image") var flag_image: String,
    @SerializedName("banner_image") var banner_image: String,
    @SerializedName("category") var categories: ArrayList<Category>,
    @SerializedName("description1") var description1: String,
    @SerializedName("description2") var description2: String
)