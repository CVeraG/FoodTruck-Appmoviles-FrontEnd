package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.R

data class Orden(
    val productos: List<Menu>,
    val total: Double,
    val fecha: String,
    var status: String
)

@Composable
fun ShowOrdersTab(navController: NavController) {
    val pedido = listOf(
        Menu(name = "Meal 1", image = R.drawable.meal_1, price = 1, quantity = 2),
        Menu(name = "Meal 2", image = R.drawable.meal_2, price = 2, quantity = 7),
    )
    val ordenes: List<Orden> = listOf(
        Orden(pedido, 123.0, "2024-01-13", "En proceso"),
        Orden(pedido, 123.0, "2021-01-13", "En proceso"),
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xfff1f1f1))
        .padding(horizontal = 8.dp))
    {
        LazyColumn {
            itemsIndexed(ordenes) { indexed, orden ->
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .clickable{
                            OpcionPedidos.value = 1
                            pedidoDetalles.value = orden

                        }

                    ) {
                    OrderItem(orden = orden)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}

@Composable
fun OrderItem(orden: Orden) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Fecha: ${orden.fecha}", fontSize = 15.sp, color = Color(0xff383838))
        Spacer(modifier = Modifier.height(8.dp))
        val totalArticulos = orden.productos.sumBy { it.quantity }
        Text(text = "Total de artículos: $totalArticulos", fontSize = 15.sp, color = Color(0xff383838))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Total: ${orden.total}", fontSize = 15.sp, color = Color(0xff383838))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Estado: ${orden.status}", fontSize = 15.sp, color = Color(0xff383838))
    }
}


@Preview
@Composable
fun ShowOrdersTabPreview() {
    val navController = rememberNavController()
    ShowOrdersTab(navController = navController)
}

