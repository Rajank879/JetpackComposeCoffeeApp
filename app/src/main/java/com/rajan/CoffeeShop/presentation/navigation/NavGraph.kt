package com.rajan.CoffeeShop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rajan.CoffeeShop.data.local.datastore.OnboardingDataStore
import com.rajan.CoffeeShop.presentation.feature.cartscreen.CartScreen
import com.rajan.CoffeeShop.presentation.feature.cartscreen.CartViewModel
import com.rajan.CoffeeShop.presentation.feature.detailsscreen.DetailsScreen
import com.rajan.CoffeeShop.presentation.feature.detailsscreen.DetailsScreenViewModel
import com.rajan.CoffeeShop.presentation.feature.favouritescreen.FavouriteScreen
import com.rajan.CoffeeShop.presentation.feature.favouritescreen.FavouriteScreenViewModel
import com.rajan.CoffeeShop.presentation.feature.homeScreen.HomeScreen
import com.rajan.CoffeeShop.presentation.feature.homeScreen.HomeViewModel
import com.rajan.CoffeeShop.presentation.feature.login.ui.LoginScreen
import com.rajan.CoffeeShop.presentation.feature.login.LoginScreenViewModel
import com.rajan.CoffeeShop.presentation.feature.profilescreen.ProfileScreen
import com.rajan.CoffeeShop.presentation.feature.profilescreen.ProfileScreenViewModel
import com.rajan.CoffeeShop.presentation.feature.signup.SignupViewModel
import com.rajan.CoffeeShop.presentation.feature.signup.ui.SignupScreen
import com.rajan.CoffeeShop.presentation.feature.welcomescreen.WelcomeScreen
import com.rajan.CoffeeShop.presentation.feature.welcomescreen.WelcomeScreenViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val hasSeen by OnboardingDataStore.hasSeenWelcomeFlow(context).collectAsState(initial = false)
//    val startDestination = if (hasSeen) Routes.HomeScreen else Routes.WelcomeScreen
    val startDestination = remember(hasSeen) {
        if (hasSeen) Routes.HomeScreen else Routes.WelcomeScreen
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routes.WelcomeScreen> {
            val viewModel: WelcomeScreenViewModel = hiltViewModel()
            WelcomeScreen(navController, viewModel)
        }
        composable<Routes.HomeScreen> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController, viewModel)
        }
        composable<Routes.DetailsScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.DetailsScreen>()
            val viewModel: DetailsScreenViewModel = hiltViewModel()

            DetailsScreen(
                productId = args.productId,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable<Routes.CartScreen> {
            val viewModel: CartViewModel = hiltViewModel()
            CartScreen(navController, viewModel)
        }
        composable<Routes.FavouriteScreen> {
            val viewModel: FavouriteScreenViewModel = hiltViewModel()
            FavouriteScreen(navController, viewModel)
        }
        composable<Routes.ProfileScreen> {
            val viewModel: ProfileScreenViewModel = hiltViewModel()
            ProfileScreen(navController, viewModel)
        }
        composable<Routes.LoginScreen> {
            val viewModel: LoginScreenViewModel = hiltViewModel()
            LoginScreen(navController, viewModel)
        }

        composable<Routes.SignupScreen> {
            val viewModel: SignupViewModel = hiltViewModel()
            SignupScreen(navController, viewModel)
        }

    }
}