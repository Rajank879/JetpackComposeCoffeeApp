package com.rajan.CoffeeShop.presentation.screen.homeScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.domain.model.Product

@Composable
fun ProductsGrid(
    products: List<Product>,
    navController: NavController,
    topContent: @Composable () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        item { topContent() }
        items(products.chunked(2)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ProductCard(
                    it[0], Modifier.weight(1f),navController
                )
                if (it.size == 2) {
                    ProductCard(
                        it[1], Modifier.weight(1f), navController
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}