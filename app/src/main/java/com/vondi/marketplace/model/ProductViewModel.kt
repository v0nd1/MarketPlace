package com.vondi.marketplace.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.marketplace.data.remote.MarketplaceApi
import com.vondi.marketplace.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    val productsLiveData = MutableLiveData<List<Product>>()

    private var currentPage = 0
    private val pageSize = 20

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = repository.fetchProducts(currentPage, pageSize)
                val products = response.products
                productsLiveData.value = products
            } catch (e: Exception) {
                println("ERROR: ${e.message}")
            }
        }
    }

    fun loadMoreProducts() {
        currentPage += 20
        fetchProducts()
    }
}