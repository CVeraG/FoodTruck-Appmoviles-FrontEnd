package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var enableSystemBackButton by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            BackHandler(enableSystemBackButton) {
                // Lógica para manejar el evento de retroceso del sistema

                    when(selectedTabIndexEmpleado.value){
                        0 ->  when (OpcionPedidosEmpleado.value){
                            0 -> selectedTabIndexEmpleado.value = 0
                            1 -> OpcionPedidosEmpleado.value = 0
                        }
                        1 -> selectedTabIndexEmpleado.value = 0
                        2 -> when (OpcionConsultaPedidosEmpleado.value){
                            0 -> selectedTabIndexEmpleado.value = 0
                            1 -> OpcionConsultaPedidosEmpleado.value = 0
                        }
                        3 -> selectedTabIndexEmpleado.value =  0
                }
            }
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                title = { Text("Food Truck", fontFamily = ubuntuFont) },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {
                            when(selectedTabIndexEmpleado.value){
                                0 ->  when (OpcionPedidosEmpleado.value){
                                    0 -> selectedTabIndexEmpleado.value = 0
                                    1 -> OpcionPedidosEmpleado.value = 0
                                }
                                1 -> selectedTabIndexEmpleado.value = 0
                                2 -> when (OpcionConsultaPedidosEmpleado.value){
                                    0 -> selectedTabIndexEmpleado.value = 0
                                    1 -> OpcionConsultaPedidosEmpleado.value = 0
                                }
                                3 -> selectedTabIndexEmpleado.value =  0
                            }
                            enableSystemBackButton = false

                        }) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                        }
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
                0 -> if(OpcionPedidosEmpleado.value == 0){
                    ShowOrdersTabEmpleado(navController = navController)
                }
                else{
                    DetallesOrdenEmpleado (navController = navController, pedidoDetalles = pedidoDetallesEmpleado.value)
                }
                1 -> AsignarPedidosScreen(navController = navController)

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


