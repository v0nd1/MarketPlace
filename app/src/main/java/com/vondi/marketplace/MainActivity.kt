package com.vondi.marketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.vondi.marketplace.model.ProductViewModel
import com.vondi.marketplace.presentation.FeedProductsScreen
import com.vondi.marketplace.ui.theme.MarketPlaceTheme

class MainActivity : ComponentActivity() {
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setContent {
            MarketPlaceTheme {
                FeedProductsScreen(productViewModel)
            }
        }
    }

    private fun initViewModel() {
        productViewModel = ViewModelProvider(this@MainActivity)[ProductViewModel::class.java]
    }
}
