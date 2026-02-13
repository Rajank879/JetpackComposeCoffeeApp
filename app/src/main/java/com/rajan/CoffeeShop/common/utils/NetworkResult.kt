package com.rajan.CoffeeShop.common.utils

import com.rajan.CoffeeShop.presentation.screen.loginscreen.LoginUiState

sealed class NetworkResult<out T> {

    data class Success<T>(val data: T) : NetworkResult<T>()

    data class Error(
        val message: String
    ) : NetworkResult<Nothing>()

    object Loading : NetworkResult<Nothing>()

    object Idle : NetworkResult<Nothing>()
}