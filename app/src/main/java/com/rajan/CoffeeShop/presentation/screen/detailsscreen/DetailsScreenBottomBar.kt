package com.rajan.CoffeeShop.presentation.screen.detailsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajan.CoffeeShop.presentation.theme.IvoryWhite
import com.rajan.CoffeeShop.presentation.theme.LightBrown
import com.rajan.CoffeeShop.presentation.ui_components.AppMessageDialog

@Preview(showBackground = true)
@Composable
fun DetailsScreenBottomBar() {
    var showCartDialog by remember { mutableStateOf(false) }
    BottomAppBar(containerColor = Color.Transparent) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Column {
                Text(text = "Price", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$9.67", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(
                onClick = { showCartDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBrown,
                    contentColor = IvoryWhite
                )
            ) {
                Text(text = "Add To Cart", fontSize = 20.sp)
            }
            AppMessageDialog(
                show = showCartDialog,
                title = "Added to cart",
                message = "Item has beed added to your cart",
                onDismiss = { showCartDialog = false }
            )
        }
    }
}