package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.ipn.escom.verac.foodtruck2.R

@Composable
fun ConsultarPedidoAsignadoScreen(navController: NavController) {
    val pedido = listOf(
        Menu(name = "Meal 1", image = R.drawable.meal_1, price = 1, quantity = 2),
        Menu(name = "Meal 2", image = R.drawable.meal_2, price = 2, quantity = 7),
    )
    val ordenes: List<OrdenEmpleado> = listOf(
        OrdenEmpleado(pedido, 123.0, "2024-01-13", "En proceso", "Juan"),
        OrdenEmpleado(pedido, 123.0, "2021-01-13", "En proceso", "Pedro"),
    )       //Esta lista es la que se deberia de llenar desde el back
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
                            OpcionConsultaPedidosEmpleado.value = 1
                            ConsultaDetallesEmpleado.value = orden

                        }

                ) {
                    OrderItemEmpleado(orden = orden)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}
