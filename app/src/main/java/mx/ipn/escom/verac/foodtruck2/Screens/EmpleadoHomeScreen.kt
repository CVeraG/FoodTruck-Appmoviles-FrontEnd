package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.components.TabLayout
import mx.ipn.escom.verac.foodtruck2.ui.theme.ubuntuFont

var selectedTabIndexEmpleado = mutableStateOf(0)
var OpcionPedidosEmpleado = mutableStateOf(0)
var pedidoDetallesEmpleado = mutableStateOf<OrdenEmpleado?>(null)
var OpcionConsultaPedidosEmpleado = mutableStateOf(0)
var ConsultaDetallesEmpleado = mutableStateOf<OrdenEmpleado?>(null)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpleadoHomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                title = { Text("Food Truck", fontFamily = ubuntuFont) },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
            // Contenido de la barra superior con el TabLayout
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 40.dp) // Ajustar manualmente el relleno superior e inferior
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp)) // Ajustar manualmente el espacio entre el título y el TabLayout
                TabLayout(
                    selectedIndex = selectedTabIndexEmpleado,
                    items = listOf(
                        "Ver Pedidos" to { if(OpcionPedidosEmpleado.value == 0){
                            ShowOrdersTabEmpleado(navController = navController)
                        }
                        else{
                            DetallesOrdenEmpleado (navController = navController, pedidoDetalles = pedidoDetallesEmpleado.value)
                        }  },
                        "Asignar" to { AsignarPedidosScreen(navController = navController) },
                        "Consultar " to{ if(OpcionConsultaPedidosEmpleado.value == 0){
                            ConsultarPedidoAsignadoScreen(navController = navController)
                        }
                        else{
                            ConsultaDetallesOrdenEmpleado (navController = navController, pedidoDetalles = ConsultaDetallesEmpleado.value)
                        }  },
                        "Entrega" to { DeliveryScreen(navController = navController) }
                    ),
                    onTabClick = { selectedTabIndexEmpleado.value = it }
                )
            }
        }
    ) { innerPaddings ->
        Box(Modifier.padding(innerPaddings)) {
            when (selectedTabIndexEmpleado.value) {
                0 -> ShowOrdersTabEmpleado(navController = navController)
                1 -> { if(OpcionPedidosEmpleado.value == 0){
                    ShowOrdersTabEmpleado(navController = navController)
                }
                else{
                    DetallesOrdenEmpleado (navController = navController, pedidoDetalles = pedidoDetallesEmpleado.value)
                }  }
                2 -> { if(OpcionConsultaPedidosEmpleado.value == 0){
                    ConsultarPedidoAsignadoScreen(navController = navController)
                }
                else{
                    ConsultaDetallesOrdenEmpleado (navController = navController, pedidoDetalles = ConsultaDetallesEmpleado.value)
                }  }
                3 -> DeliveryScreen(navController = navController)
            }
        }
    }
}


@Composable
fun AssignOrdersScreen(navController: NavController) {
    // Lógica para la pestaña de asignar pedidos
    // Puedes implementar aquí la interfaz para asignar pedidos a los empleados
}

@Composable
fun ShowAssignedOrders(navController: NavController) {
    // Lógica para la pestaña de consultar pedidos asignados
    // Puedes implementar aquí la interfaz para ver los pedidos asignados a un empleado
}

@Composable
fun DeliveryScreen(navController: NavController) {
    // Lógica para la pestaña de entrega
    // Puedes implementar aquí la interfaz para gestionar la entrega de pedidos
}

@Preview
@Composable
fun EmpleadoHomeScreenPreview() {
    val navController = rememberNavController()
    EmpleadoHomeScreen(navController)
}