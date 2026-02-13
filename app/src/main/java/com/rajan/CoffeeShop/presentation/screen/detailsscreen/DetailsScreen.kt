package com.rajan.CoffeeShop.presentation.screen.detailsscreen


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rajan.CoffeeShop.domain.model.Product
import com.rajan.CoffeeShop.R

@Composable
fun DetailsScreen(productId: Int, navController: NavController, viewModel: DetailsScreenViewModel) {
    val products = listOf(
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
        ),
        Product(
            id = 4,
            name = "Mocha",
            description = "With Cocoa Flavour",
            price = 2.89,
            imageRes = R.drawable.coffee_4
        ),
        Product(
            id = 5,
            name = "Macchiato",
            description = "Bold & Milky",
            price = 7.50,
            imageRes = R.drawable.coffee_5
        ),
        Product(
            id = 6,
            name = "Flat White",
            description = "Velvety Smooth",
            price = 2.34,
            imageRes = R.drawable.coffee_6
        ),
        Product(
            id = 7,
            name = "Iced Mocha",
            description = "Refreshing & Rich",
            price = 3.80,
            imageRes = R.drawable.coffee_3
        ),
    )
    val selectedProduct = products.find { it.id == productId }
    if (selectedProduct == null) {
        navController.popBackStack()
        return
    }
    Scaffold(
        topBar = { DetailsScreenTopbar(navController) },
        bottomBar = { DetailsScreenBottomBar() }) { innerPadding ->
        ProductDetailContent(selectedProduct, innerPadding)
    }
}