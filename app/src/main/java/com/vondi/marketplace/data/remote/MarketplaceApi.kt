package com.vondi.marketplace.data.remote

import com.vondi.marketplace.model.Product
import com.vondi.marketplace.model.ProductResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketplaceApi {
    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ProductResponse

    companion object {
        fun create(): MarketplaceApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MarketplaceApi::class.java)
        }
    }
}