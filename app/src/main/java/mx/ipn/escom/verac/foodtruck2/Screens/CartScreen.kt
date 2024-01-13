package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.Screens.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@Composable
fun ShowCartScreen(navController: NavController, selectedProducts: List<Menu>) {
    var isConfirmationDialogVisible by remember { mutableStateOf(false) }
    var productToDelete: Menu? by remember { mutableStateOf(null) }

    // Crear una nueva lista que solo contiene elementos con quantity diferente de 0
    val visibleSelectedProducts = selectedProducts.filter { it.quantity != 0 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Utilizar un Box para colocar la cuadrícula de productos en la parte superior
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)  // Hace que la cuadrícula ocupe el espacio disponible
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(visibleSelectedProducts) { product ->
                    CartItem(
                        product = product,
                        onRemoveClick = {
                            // Mostrar el diálogo de confirmación antes de eliminar
                            isConfirmationDialogVisible = true
                            productToDelete = product
                            val productToUpdate = allProducts.find { it.name == product.name }
                            productToUpdate?.quantity = 0
                            // Actualizar la lista mutable allProducts
                            allProducts = mutableStateListOf(*allProducts.toTypedArray())

                        }
                    )
                }
            }

            // Agrega el diálogo de confirmación
            if (isConfirmationDialogVisible) {
                DeleteConfirmationDialog(
                    onConfirm = {
                        // Eliminar el producto y ocultar el diálogo de confirmación
                        productToDelete?.quantity = 0
                        isConfirmationDialogVisible = false
                        productToDelete = null
                    },
                    onDismiss = {
                        // Ocultar el diálogo de confirmación si se cancela
                        isConfirmationDialogVisible = false
                        productToDelete = null
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón siempre visible en la parte inferior
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Button(
                onClick = {
                    // Lógica para procesar la compra
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Comprar")
            }
        }
    }
}


@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Eliminar producto") },
        text = { Text("¿Estás seguro de que deseas eliminar este producto?") },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
                onDismiss()
            }) {
                Text("Sí")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text("No")
            }
        }
    )
}

@Composable
fun CartItem(product: Menu, onRemoveClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable { /* Puedes agregar lógica de clic aquí */ }
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.name, fontSize = 15.sp, color = Color(0xff383838))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${product.price}$")
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Cantidad: ${product.quantity}")
            Spacer(modifier = Modifier.height(16.dp))
            // Agrega el botón de eliminar
            TextButton(onClick = onRemoveClick) {
                Text("Eliminar")
            }
        }
    }
}

