package com.vondi.marketplace.presentation.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = Color.Gray
    )
}