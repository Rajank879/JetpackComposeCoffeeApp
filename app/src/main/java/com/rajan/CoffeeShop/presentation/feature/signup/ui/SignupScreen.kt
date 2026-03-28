package com.rajan.CoffeeShop.presentation.feature.signup.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rajan.CoffeeShop.presentation.feature.signup.SignUpEvent
import com.rajan.CoffeeShop.presentation.feature.signup.SignupViewModel
import com.rajan.CoffeeShop.presentation.navigation.Routes

@Composable
fun SignupScreen(navController: NavController, viewModel: SignupViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        if (uiState.isSuccess){
            navController.navigate(Routes.LoginScreen){
                popUpTo(Routes.SignupScreen){
                    inclusive = true
                }
            }
        }
        uiState.error?.let {
            snackBarHostState.showSnackbar(it)
            viewModel.onEvent(SignUpEvent.ClearError)
        }
    }
    Scaffold(
        snackbarHost = { snackBarHostState }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SignupContent(
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}