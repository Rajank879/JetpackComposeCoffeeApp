package com.rajan.CoffeeShop.presentation.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajan.CoffeeShop.presentation.feature.home.HomeEvent

@Composable
fun ModernSearchBar( query: String,onEvent: (HomeEvent) -> Unit) {
    val colors = MaterialTheme.colorScheme
//    var query by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable {
                onEvent(HomeEvent.OnSearchQueryChange(query))
            },
        shape = RoundedCornerShape(40.dp),
        tonalElevation = 4.dp,
        color = colors.surfaceVariant
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = colors.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(12.dp))

            if (query.isEmpty()) {
                Text(
                    text = "Search your coffee...",
                    color = colors.onSurfaceVariant.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            } else {
                Text(
                    text = query,
                    color = colors.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
        }
    }
}