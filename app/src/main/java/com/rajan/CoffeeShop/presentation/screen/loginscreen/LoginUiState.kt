package com.rajan.CoffeeShop.presentation.screen.loginscreen

import com.rajan.CoffeeShop.data.remote.model.LoginResponse
import com.rajan.CoffeeShop.domain.model.User

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}