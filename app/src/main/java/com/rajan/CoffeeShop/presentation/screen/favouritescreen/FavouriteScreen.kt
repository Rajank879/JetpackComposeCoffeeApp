package com.rajan.CoffeeShop.presentation.screen.favouritescreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.domain.model.Product
import com.rajan.CoffeeShop.presentation.screen.cartscreen.CartScreenTopBar
import com.rajan.CoffeeShop.presentation.ui_components.MyBottomNavBar

@Composable
fun FavouriteScreen(navController : NavController) {
    var favouriteProducts by remember { mutableStateOf(listOf(
        Product(
            id = 1,
            name = "Espresso",
            description = "Strong & Rich",
            price = 3.80,
            imageRes = R.drawable.coffee_1
        ),
        Product(
            id = 2,
            name = "Latte",
            description = "Smooth & Creamy",
            price = 4.80,
            imageRes = R.drawable.coffee_2
        ),
        Product(
            id = 3,
            name = "Copuccino",
            description = "With Chocolate",
            price = 6.80,
            imageRes = R.drawable.coffee_3
        )
    )) }

    Scaffold(topBar = {CartScreenTopBar(navController, "Wishlist") },
        bottomBar = {MyBottomNavBar(navController, "Favourite")}) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(it)) {

            item {
                Spacer(modifier = Modifier.height(24.dp))
                favouriteProducts.forEach { item ->
                    FavouriteItemCard(
                        item,
                        onRemove = {
                            favouriteProducts = favouriteProducts.filter { it != item }
                        }
                    )
                }
            }
        }
    }
}