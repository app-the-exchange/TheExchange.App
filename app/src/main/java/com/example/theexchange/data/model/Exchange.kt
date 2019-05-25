package com.example.theexchange.data.model

import com.google.gson.annotations.SerializedName

data class Exchange(
    @SerializedName("idcostumer") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("startdate") var startDate: String,
    @SerializedName("course") var course: String,
    @SerializedName("position") var position: String,
    @SerializedName("idcountry") var idCountry: Int,
    @SerializedName("idcustomer_app") var idCustomer: String,
    @SerializedName("code") var code: String
) {
}