package com.rajan.CoffeeShop.presentation.feature.home

import com.google.android.libraries.places.api.model.Place

sealed class HomeEvent {
        data class OnAddressClick(val address: String= "") : HomeEvent()
        data class OnSearchQueryChange(val query: String = "") : HomeEvent()
        data class OnCategorySelected(val category: String) : HomeEvent()
        data class OnProductClick(val productId: Int) : HomeEvent()
        data class OnPlaceSelected(val place: Place) : HomeEvent()
        object OnRefresh : HomeEvent()
}