package br.com.theexchange.presentation.ui.exchange_category.model

import com.google.gson.annotations.SerializedName

data class ExchangeCategoryDTO(
    @SerializedName("idcategory_customer") var idCategoryCustomer: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("background_image") var backgroundImage: String,
    @SerializedName("idcountry") var idCountry: String,
    @SerializedName("idcustomer") var idCustomer: String
)