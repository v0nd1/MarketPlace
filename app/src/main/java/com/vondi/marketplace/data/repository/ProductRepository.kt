package com.vondi.marketplace.data.repository

import androidx.lifecycle.MutableLiveData
import com.vondi.marketplace.data.remote.MarketplaceApi
import com.vondi.marketplace.model.Product
import com.vondi.marketplace.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {
    private val productService = MarketplaceApi.create()

    suspend fun fetchProducts(skip: Int, limit: Int): ProductResponse {
        return withContext(Dispatchers.IO) {
            productService.getProducts(skip = skip, limit = limit)
        }
    }
}
