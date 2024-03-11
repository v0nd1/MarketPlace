package com.vondi.marketplace.model

data class ProductResponse(
    val products: List<Product>,
    val totalPages: Int
)
