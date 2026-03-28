package com.rajan.CoffeeShop.presentation.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rajan.CoffeeShop.domain.model.Products.Products
import com.rajan.CoffeeShop.presentation.feature.home.HomeUiState
import kotlin.math.roundToInt




@Composable
fun ProductCard(product: Products, uiState: HomeUiState) {
    val color = MaterialTheme.colorScheme

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal =8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.surface
        )
    ) {
//        AnimatedVisibility(visible = uiState.isLoading) {
//            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
//        }
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
             Box(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(140.dp)
             ) {
                 AsyncImage(
                     model = product.thumbnail,
                        contentDescription = product.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(16.dp))
                 )
//                 Image(
//                     painter = painterResource(id = R.drawable.coffee_3),
//                     contentDescription = null,
//                     contentScale = ContentScale.Crop,
//                     modifier = Modifier.fillMaxSize()
//                         .clip(RoundedCornerShape(16.dp))
//                 )

                 IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(color = color.surfaceVariant.copy(0.6f), shape = CircleShape)
                    ) {
                     Icon(
                         imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = color.primary
                     )
                    }
             }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.description.orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                color = color.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.brand.orEmpty(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            RatingBar(product.rating?:5.0)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val actualPrice = product.price ?: 0.0
                val discount = product.discountPercentage ?: 0.0
                val pp = ((actualPrice + (actualPrice * (discount / 100))) * 100).roundToInt() / 100.0
                Text(
                    text = "$${pp}",
                    style = MaterialTheme.typography.bodySmall,
                    color =color.onSurfaceVariant,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {/*TODO*/ },
                ){
                    Icon(
                        imageVector = Icons.Default.AddShoppingCart,
                        contentDescription = "Add to Cart",
                        tint = color.primary
                    )
                }
            }
        }
    }
}