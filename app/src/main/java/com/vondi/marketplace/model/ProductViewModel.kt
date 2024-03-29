package com.vondi.marketplace.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.marketplace.data.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class STATE {
    LOADING,
    SUCCESS,
    FAILED
}

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    var productsResponse: List<Product> by mutableStateOf(listOf())
    var category: String by mutableStateOf("electronics")
    private var skip:Int by mutableIntStateOf(0)
    private var errorMessage: String by mutableStateOf("")
    var state by mutableStateOf(STATE.LOADING)


//    fun chooseCategory(newCategory: String) {
//        category = newCategory
//        skip = 0
//        loadProducts()
//    }

//    fun loadMoreProducts() {
//        skip += 20
//        loadProducts()
//    }
    fun loadMoreProducts() {
        viewModelScope.launch {
            state = STATE.LOADING
            delay(500)
            try{
                val response = repository.fetchProducts(skip = skip, limit = 20, category = category)
                productsResponse = productsResponse.plus(response.products)
                skip += 20
                state = STATE.SUCCESS
            }catch (e: Exception){
                errorMessage = e.message.toString()
                state = STATE.FAILED
            }
        }
    }
}