package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.ipn.escom.verac.foodtruck2.R

@Composable
fun DeliveryScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }

    val pedido = listOf(
        Menu(name = "Meal 1", image = R.drawable.meal_1, price = 1, quantity = 2),
        Menu(name = "Meal 2", image = R.drawable.meal_2, price = 2, quantity = 7),
    )
    val ordenes: List<Orden> = listOf(
        Orden(pedido, 123.0, "2024-01-13", "En camino"),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    )
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
                        .clickable {
                        }

                ) {
                    OrderItem(orden = orden)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            showDialog = true
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                    ) {
                        Text(text = "Pedido Exitoso")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            showDialog2 = true

                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                    ) {
                        Text(text = "Pedido Cancelado")
                    }
                }
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Cierra el cuadro de diálogo sin realizar ninguna acción
                    showDialog = false
                },
                title = {
                    Text(text = "¿Estás seguro de completar la orden?")
                },
                confirmButton = {
                    TextButton(onClick = {
                        //Aqui mero debe de actualizar el Pedido exitoso en el back
                        println(ordenes[0].status)
                        ordenes[0].status = "Exitoso"
                        println(ordenes[0].status)
                        showDialog = false


                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false

                    }) {
                        Text("No")
                    }
                }
            )
        }

        if (showDialog2) {
            AlertDialog(
                onDismissRequest = {
                    // Cierra el cuadro de diálogo sin realizar ninguna acción
                    showDialog2 = false
                },
                title = {
                    Text(text = "¿Estás seguro de cancelar la orden?")
                },
                confirmButton = {
                    TextButton(onClick = {
                        //Aqui mero debe de actualizar el Pedido exitoso en el back
                        println(ordenes[0].status)
                        ordenes[0].status = "Cancelado"
                        println(ordenes[0].status)
                        showDialog2 = false


                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog2 = false

                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}