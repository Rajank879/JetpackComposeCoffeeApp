package com.rajan.CoffeeShop.presentation.feature.signup

sealed class SignupNavigation {
    object NavigateToLogin : SignupNavigation()
    object NavigateToHome : SignupNavigation()
}