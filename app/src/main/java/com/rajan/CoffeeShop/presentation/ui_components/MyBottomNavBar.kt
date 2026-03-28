package com.rajan.CoffeeShop.presentation.ui_components


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.presentation.navigation.Routes


@Composable
fun MyBottomNavBar(navController: NavController, route: String) {

    val colorScheme = MaterialTheme.colorScheme
    val systemUiController = rememberSystemUiController()

    // Set status bar color
    systemUiController.setStatusBarColor(
        color = colorScheme.secondary,
        darkIcons = !isSystemInDarkTheme()
    )
    val navItems = listOf(
        NavItem("Home", R.drawable.regular_outline_home, Routes.HomeScreen),
        NavItem("Cart", R.drawable.regular_outline_bag, Routes.CartScreen),
        NavItem("Favourite", R.drawable.regular_outline_heart, Routes.FavouriteScreen),
        NavItem("Profile", R.drawable.outline_account_circle_24, Routes.ProfileScreen)
    )
        NavigationBar(
            containerColor = colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            navItems.forEach { navItem ->
                val isSelected = route == navItem.title
                NavigationBarItem(
                    selected = isSelected,
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
                    label = {
                        Text(
                            text = navItem.title,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.primary,
                        selectedTextColor = colorScheme.primary,
                        unselectedIconColor = colorScheme.onSurfaceVariant,
                        unselectedTextColor = colorScheme.onSurfaceVariant,
                        indicatorColor = colorScheme.primary.copy(alpha = 0.15f)
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