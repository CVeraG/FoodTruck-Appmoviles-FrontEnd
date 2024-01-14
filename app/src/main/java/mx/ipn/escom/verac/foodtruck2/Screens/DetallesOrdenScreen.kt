package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.R
@Composable
fun DetallesOrden(navController: NavController, pedidoDetalles: Orden?) {
    var showDialog by remember { mutableStateOf(false) }
    if (pedidoDetalles != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfff1f1f1))
                .padding(16.dp)
        ) {
            // Texto "Detalles del Pedido"
            Text(
                text = "Detalles del Pedido",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Fecha: ${pedidoDetalles.fecha}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Total de artículos: ${pedidoDetalles.productos.sumBy { it.quantity }}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Total: ${pedidoDetalles.total}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Estado: ${pedidoDetalles.status}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Texto "Tu pedido"
            Text(
                text = "Tu pedido",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Usar un Box con Modifier.fillMaxWidth para limitar el ancho del LazyHorizontalGrid
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),  // Puedes ajustar la altura según tus necesidades
                contentAlignment = Alignment.CenterStart
            ) {
                LazyHorizontalGrid(
                    modifier = Modifier
                        .wrapContentSize()  // Ajusta el tamaño al contenido
                        .padding(8.dp),  // Agrega un espacio uniforme alrededor del LazyHorizontalGrid

                    rows = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(pedidoDetalles.productos) { product ->
                        CompraItem(
                            product = product
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (pedidoDetalles.status == "En proceso") {
                Button(
                    onClick = {
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()  // Hace que el botón ocupe todo el ancho disponible
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Cancelar Orden")
                }
            }

            // Mostrar el cuadro de diálogo si showDialog es verdadero
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        // Cierra el cuadro de diálogo sin realizar ninguna acción
                        showDialog = false
                    },
                    title = {
                        Text(text = "¿Estás seguro de cancelar la orden?")
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            //Aqui mero debe de actualizar el cancelar en el back
                            pedidoDetalles.status = "Cancelado"
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
        }
    }
}

@Composable
@Preview
fun DetallesOrdenPreview() {
    val navController = rememberNavController()
    val pedidoDetalles = Orden(
        productos = listOf(
            Menu(name = "Meal 1", image = R.drawable.meal_1, price = 10, quantity = 2),
            Menu(name = "Meal 2", image = R.drawable.meal_2, price = 12, quantity = 1),
        ),
        total = 123.0,
        fecha = "2024-01-13",
        status = "En proceso"
    )

    DetallesOrden(navController = navController, pedidoDetalles = pedidoDetalles)
}

