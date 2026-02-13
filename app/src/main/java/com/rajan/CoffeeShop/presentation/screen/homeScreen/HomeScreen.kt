package com.rajan.CoffeeShop.presentation.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.domain.model.Product
import com.rajan.CoffeeShop.presentation.ui_components.MyBottomNavBar

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val location = "Bellandur, Bengaluru"
    Scaffold(bottomBar = {
        MyBottomNavBar(navController, "Home")
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f / 3f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF303030),
                            Color(0xFF1F1F1F),
                            Color(0xFF121212)
                        )
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {


            // Displaying Product
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
            ProductsGrid(products = products, navController) {
                Text(
                    text = "Location",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = location, color = Color.White, fontWeight = FontWeight.SemiBold)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = Color.White,
                        contentDescription = "Change Location"
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                MySearchBar()
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.banner_1),
                    contentDescription = "Banner"
                )
                Spacer(modifier = Modifier.height(12.dp))
                HomeScreenCategories()
            }
        }

    }
}