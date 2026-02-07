package com.rajan.CoffeeShop.presentation.screen.cartscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rajan.CoffeeShop.R
import com.rajan.CoffeeShop.domain.model.Product
import com.rajan.CoffeeShop.presentation.theme.LightBrown
import com.rajan.CoffeeShop.presentation.ui_components.MyBottomNavBar

@Composable
fun CartScreen(navController: NavController) {
    // Displaying Product
    val cartProducts = listOf(
        Product(
            id = 1,
            name = "Espresso",
            description = "Strong & Rich",
            price = 3.80,
            imageRes = R.drawable.coffee_1
        ),
        Product(
            id = 2,
            name = "Latte",
            description = "Smooth & Creamy",
            price = 4.80,
            imageRes = R.drawable.coffee_2
        ),
        Product(
            id = 3,
            name = "Copuccino",
            description = "With Chocolate",
            price = 6.80,
            imageRes = R.drawable.coffee_3
        )
    )
    val amount = cartProducts.sumOf { it.price }
    val amountFormatted = String.format(java.util.Locale.US, "%.2f", amount)
    val deliveryFee = 1.00
    val totalAmount = amount + deliveryFee

    Scaffold(topBar = { CartScreenTopBar(navController, "Order") },
        bottomBar = { MyBottomNavBar(navController, "Cart") }) { innerPadding ->
        LazyColumn(
            Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            item {
                Text(
                    text = "Deliver",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBrown
                )
                Spacer(modifier = Modifier.height(16.dp))
                cartProducts.forEach { product ->
                    CartItemCard(product)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Payment Summary",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Price",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "$ $amountFormatted",
                        fontSize = 18.sp
                    )

                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Delivery Fee",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "$ $deliveryFee",
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                PaymentMethodCard(totalAmount)
            }
        }
    }
}