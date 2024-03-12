package com.vondi.marketplace.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("title") var title : String,
    @SerializedName("description") var description : String,
    @SerializedName("price") var price : Int? = null,
    @SerializedName("discountPercentage") var discountPercentage : Double? = null,
    @SerializedName("rating") var rating : Double? = null,
    @SerializedName("stock") var stock : Int? = null,
    @SerializedName("brand") var brand : String? = null,
    @SerializedName("category") var category : String? = null,
    @SerializedName("thumbnail") var thumbnail : String? = null,
    @SerializedName("images") var images : ArrayList<String> = arrayListOf()
)
