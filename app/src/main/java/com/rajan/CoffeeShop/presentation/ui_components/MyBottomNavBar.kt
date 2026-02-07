package com.rajan.CoffeeShop.presentation.ui_components


import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.presentation.navigation.Routes
import com.rajan.CoffeeShop.presentation.theme.LightBrown


@Composable
fun MyBottomNavBar(navController: NavController, route: String) {

    val navitems = listOf(
        NavItem("Home", R.drawable.regular_outline_home, Routes.HomeScreen),
        NavItem("Cart", R.drawable.regular_outline_bag, Routes.CartScreen),
        NavItem("Favourite", R.drawable.regular_outline_heart, Routes.FavouriteScreen),
        NavItem("Profile", R.drawable.outline_account_circle_24, Routes.ProfileScreen)
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        navitems.forEachIndexed { index, navItem ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(navItem.routes) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = navItem.icon),
                        contentDescription = navItem.title,
                        Modifier.size(24.dp)
                    )
                },
                label = { Text(text = navItem.title) },
                modifier = Modifier.size(70.dp),
                alwaysShowLabel = false,
                selected = navItem.title == route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LightBrown,
                    selectedTextColor = LightBrown,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                )
            )
        }

    }
}

data class NavItem(
    val title: String,
    val icon: Int,
    val routes: Routes
)