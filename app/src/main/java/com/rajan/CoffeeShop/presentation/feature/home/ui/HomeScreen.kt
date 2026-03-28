package com.rajan.CoffeeShop.presentation.feature.home.ui

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.rajan.CoffeeShop.presentation.feature.home.HomeEvent
import com.rajan.CoffeeShop.presentation.feature.home.HomeNavigation
import com.rajan.CoffeeShop.presentation.feature.home.HomeViewModel
import com.rajan.CoffeeShop.presentation.navigation.Routes
import com.rajan.CoffeeShop.presentation.ui_components.FullScreenLoader
import com.rajan.CoffeeShop.presentation.ui_components.MyBottomNavBar

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {

    val context = LocalContext.current
    val activity = context as Activity
    val color = MaterialTheme.colorScheme
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val pagingItems = viewModel.pagingData.collectAsLazyPagingItems()
    val total = pagingItems.itemCount
    val totalProducts by viewModel.totalProducts.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        if (result.resultCode == Activity.RESULT_OK && data != null) {
            val place = Autocomplete.getPlaceFromIntent(data)
            viewModel.onEvent(HomeEvent.OnPlaceSelected(place))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationState.collect { navigation ->
            when (navigation) {
                HomeNavigation.NavigateToSearch -> {
                    navController.navigate(Routes.SearchScreen)
                }

                HomeNavigation.NavigateToPlacesAutocomplete -> {
                    val intent = Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, // or FULLSCREEN
                        listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
                    ).build(context)
                    launcher.launch(intent)
                }

                else -> {}
            }
        }
    }
    LaunchedEffect(uiState) {
        uiState.apiError?.let {
            snackBarHostState.showSnackbar(it)
            viewModel.onEvent(HomeEvent.OnRefresh)
        }
    }

    Scaffold(
        bottomBar = {
            MyBottomNavBar(navController, "Home")
        },
        containerColor = color.background
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) { HomeHeader(viewModel::onEvent, uiState) }
            item(span = { GridItemSpan(maxLineSpan) }) { Spacer(modifier = Modifier.height(4.dp)) }
            item(span = { GridItemSpan(maxLineSpan) }) {
                HomeCategoriesSection(
                    viewModel::onEvent,
                    uiState
                )
            }

            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { product ->
                    ProductCard(product, uiState)
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    modifier = Modifier.padding(8.dp)
                        .fillMaxWidth(),
                    text = "Showing $total of $totalProducts products",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold, color = color.onBackground),
                    textAlign = TextAlign.End
                )
            }

        }
        if (uiState.isLoading) {
            FullScreenLoader()
        }
    }
}
