package com.rajan.CoffeeShop.presentation.feature.login

sealed class LoginNavigation {
    object NavigateToHome : LoginNavigation()
    object NavigateToRegister : LoginNavigation()
    object NavigateToForgotPassword : LoginNavigation()

}