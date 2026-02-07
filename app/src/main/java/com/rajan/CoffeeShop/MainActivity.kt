package com.rajan.CoffeeShop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rajan.CoffeeShop.presentation.navigation.NavGraph
import com.rajan.CoffeeShop.presentation.screen.cartscreen.CartScreen
import com.rajan.CoffeeShop.presentation.screen.detailsscreen.DetailsScreen
import com.rajan.CoffeeShop.presentation.screen.favouritescreen.FavouriteScreen
import com.rajan.CoffeeShop.presentation.screen.homeScreen.HomeScreen
import com.rajan.CoffeeShop.presentation.theme.CoffeeShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopTheme {
//                HomeScreen()
//                DetailsScreen()
                NavGraph()
//                CartScreen()
//                FavouriteScreen()
            }
        }
    }
}

