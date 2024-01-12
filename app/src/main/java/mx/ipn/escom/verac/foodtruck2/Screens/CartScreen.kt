package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CartScreen(cartItems: List<Pair<Menu, Int>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar los elementos del carrito
        for ((item, quantity) in cartItems) {
            // Puedes personalizar cómo mostrar cada elemento del carrito
            Text(text = "${item.name} - Cantidad: $quantity")
        }

        // Otros elementos de la vista del carrito, como total, botones, etc.
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}