package com.rajan.CoffeeShop.presentation.feature.home

sealed class HomeNavigation {
    object NavigateToSearch : HomeNavigation()
    object NavigateToPlacesAutocomplete : HomeNavigation()
    data class NavigateToProductDetails(val productId: String) : HomeNavigation()

}