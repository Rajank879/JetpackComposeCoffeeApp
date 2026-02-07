package com.rajan.CoffeeShop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rajan.CoffeeShop.data.OnboardingDataStore
import com.rajan.CoffeeShop.presentation.screen.cartscreen.CartScreen
import com.rajan.CoffeeShop.presentation.screen.detailsscreen.DetailsScreen
import com.rajan.CoffeeShop.presentation.screen.favouritescreen.FavouriteScreen
import com.rajan.CoffeeShop.presentation.screen.homeScreen.HomeScreen
import com.rajan.CoffeeShop.presentation.screen.profilescreen.ProfileScreen
import com.rajan.CoffeeShop.presentation.screen.welcomescreen.WelcomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val hasSeen by OnboardingDataStore.hasSeenWelcomeFlow(context).collectAsState(initial = false)
    val startDestination = if (hasSeen) Routes.HomeScreen else Routes.WelcomeScreen


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routes.WelcomeScreen> { WelcomeScreen(navController) }
        composable<Routes.HomeScreen> { HomeScreen(navController) }
        composable<Routes.DetailsScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.DetailsScreen>()
            DetailsScreen(args.productId, navController)
        }
        composable<Routes.CartScreen> { CartScreen(navController) }
        composable<Routes.FavouriteScreen> { FavouriteScreen(navController) }
        composable<Routes.ProfileScreen> { ProfileScreen(navController) }

    }
}