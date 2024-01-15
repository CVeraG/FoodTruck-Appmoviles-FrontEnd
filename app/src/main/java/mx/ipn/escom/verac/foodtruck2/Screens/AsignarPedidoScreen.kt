package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AsignarPedidosScreen(navController: NavController) {
    var selectedEmpleado by remember { mutableStateOf("Juan") }
    var selectedPedidoId by remember { mutableStateOf(0) } // Nuevo estado para el ID de pedido
    val empleados: List<String> = listOf("Juan", "Pedro", "Jorge")
    val pedidos: List<Int> = listOf(1, 2, 3) // Lista de IDs de pedido (puedes cambiarlos según tus necesidades)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(16.dp)
    ) {
        Text(
            text = "Selecciona un empleado:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            itemsIndexed(empleados) { index, empleado ->
                EmpleadoItem(
                    empleado = empleado,
                    isSelected = empleado == selectedEmpleado,
                    onSelected = {
                        selectedEmpleado = empleado
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Empleado seleccionado: $selectedEmpleado",
            fontSize = 16.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nuevo menú para seleccionar un ID de pedido
        Text(
            text = "Selecciona un ID de pedido:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            itemsIndexed(pedidos) { index, pedidoId ->
                PedidoItem(
                    pedidoId = pedidoId,
                    isSelected = pedidoId == selectedPedidoId,
                    onSelected = {
                        selectedPedidoId = pedidoId
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "ID de pedido seleccionado: $selectedPedidoId",
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Nuevo botón "Asignar pedido"
        Button(
            onClick = {
                      var cadenaBack: String = ("pedido id: $selectedPedidoId, empleado_id: $selectedEmpleado")
                //AQUI MERO LO JALAN AL BACK


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Asignar pedido")
        }
    }
}

@Composable
fun PedidoItem(
    pedidoId: Int,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = isSelected,
                onClick = { onSelected() }
            )
            .padding(16.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected() },
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 16.dp)
        )
        Text(
            text = "Pedido #$pedidoId", // Puedes personalizar el texto según tus necesidades
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 16.dp)
        )
    }
}

@Composable
fun EmpleadoItem(
    empleado: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = isSelected,
                onClick = { onSelected() }
            )
            .padding(16.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected() },
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 16.dp)
        )
        Text(
            text = empleado,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 16.dp)
        )
    }
}