package com.vondi.marketplace.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.vondi.marketplace.model.Product
import com.vondi.marketplace.model.ProductViewModel
import com.vondi.marketplace.presentation.components.LoadingState
import com.vondi.marketplace.presentation.components.ThemeButton
import com.vondi.marketplace.ui.theme.Gray
import com.vondi.marketplace.ui.theme.Gray2


@Composable
fun FeedProductsScreen(viewModel: ProductViewModel){
    val products by viewModel.productsLiveData.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(products) {product ->
                ProductCard(product)
            }
        }
    )
}


@Composable
private fun ProductCard(
    product: Product
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10))
            .background(Gray)
            .border(0.2.dp, Gray2),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .wrapContentSize(),
                shape = RoundedCornerShape(10)
            ) {
                SubcomposeAsyncImage(
                    model = product.thumbnailUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(150.dp)
                        .width(150.dp)
                        ,
                    contentScale = ContentScale.Crop,
                    loading = {
                        LoadingState()
                    },
                    error = {
                        Text(text = "Ошибка загрузки")
                    }
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.title,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.description,
                    lineHeight = 15.sp,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                ThemeButton(title = "Add to Cart", onClick = {})
                Spacer(modifier = Modifier.height(20.dp))
            }

        }

    }

}
@Composable
fun FeedProductsScreen2(products: List<Product>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}


@Composable
fun ProductCard2(
    product: Product
){
    Column(
        modifier = Modifier
            .height(200.dp)
            .border(0.5.dp, Color.Gray, RoundedCornerShape(10)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SubcomposeAsyncImage(
            model = product.thumbnailUrl,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(150.dp),
            contentScale = ContentScale.Crop,
            loading = {
                LoadingState()
            },
            error = {
                Text(text = "Ошибка загрузки")
            }
        )
        Text(
            text = product.title,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}




