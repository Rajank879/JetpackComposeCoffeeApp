package com.rajan.CoffeeShop.presentation.screen.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun HomeScreenCategories() {
    val allCategories = listOf("All Coffees" , "Machhiato", "Latte", "Americano", "Snacks", "Deserts")
    var selectedCategories by remember {
        mutableStateOf(allCategories[0])
    }
    LazyRow(modifier = Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(allCategories){
            CategoriesCard(text = it,
                checked = it == selectedCategories, onSelected = {selectedCategories = it})
        }
    }
}