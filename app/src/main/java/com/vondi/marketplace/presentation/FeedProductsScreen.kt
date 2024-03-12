package com.vondi.marketplace.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.vondi.marketplace.model.Product
import com.vondi.marketplace.model.ProductViewModel
import com.vondi.marketplace.model.STATE
import com.vondi.marketplace.presentation.components.LoadingState
import com.vondi.marketplace.presentation.components.ThemeButton
import com.vondi.marketplace.ui.theme.Gray
import com.vondi.marketplace.ui.theme.Gray2


@Composable
fun FeedProductsScreen(viewModel: ProductViewModel){

    // Проверка на последний итем в LazyColumn
    val scrollState = rememberLazyGridState()
    val isEnd by remember{
        derivedStateOf {
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                    scrollState.layoutInfo.totalItemsCount - 1
        }
    }

    // В случае если последний итем LazyColumn то подгружаем еще 20
    LaunchedEffect(key1 = isEnd, block = {
        viewModel.loadMoreProducts()
    } )

    Box(modifier = Modifier.fillMaxSize()){
        // Сам LazyColumn с продуктами
        LazyVerticalGrid(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(viewModel.productsResponse) {_, product ->
                    ProductCard(product)
                }
            }
        )

        // Загрузка продуктов
        if(viewModel.state == STATE.LOADING){
            LoadingProducts()
        }
    }

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
            .border(0.2.dp, Gray2)
            .clickable { println(product.id) }
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
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(150.dp)
                        .width(150.dp),
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
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.description,
                    lineHeight = 15.sp,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                ThemeButton(title = "Add to Cart", onClick = {})
                Spacer(modifier = Modifier.height(20.dp))
            }

        }

    }
}

@Composable
private fun LoadingProducts(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .alpha(0.7f)
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(50.dp))
        }
    }
}





