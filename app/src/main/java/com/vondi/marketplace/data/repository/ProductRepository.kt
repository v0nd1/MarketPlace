package com.vondi.marketplace.data.repository

import com.vondi.marketplace.data.remote.MarketplaceApi
import com.vondi.marketplace.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {
    private val productService = MarketplaceApi.create()

    suspend fun fetchProducts(
        skip: Int,
        limit: Int,
        category: String
    ): ProductResponse {
        return withContext(Dispatchers.IO) {
            productService.getProducts(
                skip = skip,
                limit = limit,
                category = category
            )
        }
    }

}
