package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.R
import mx.ipn.escom.verac.foodtruck2.ui.theme.ubuntuFont

@Composable
fun ShowComprarScreen(navController: NavController, selectedProducts: List<Menu>) {
    val campos = remember {
        mutableStateOf(
            mapOf(
                "edificio" to "",
                "piso" to "",
                "salon" to "",
                "instrucciones" to ""
            )
        )
    }
    val totalCompra = selectedProducts.sumOf { it.price * it.quantity }
    fun buildDireccion() =
        "${campos.value["edificio"]} ${campos.value["piso"]} ${campos.value["salon"]} ${campos.value["instrucciones"]}"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(22.dp))
        CompraTextField(
            value = campos.value["edificio"] ?: "",
            onValueChange = { newValue -> campos.value += "edificio" to newValue },
            label = "Edificio"
        )
        Spacer(modifier = Modifier.height(22.dp))

        CompraTextField(
            value = campos.value["piso"] ?: "",
            onValueChange = { newValue -> campos.value += "piso" to newValue },
            label = "Piso"
        )
        Spacer(modifier = Modifier.height(22.dp))
        CompraTextField(
            value = campos.value["salon"] ?: "",
            onValueChange = { newValue -> campos.value += "salon" to newValue },
            label = "Salon"
        )
        Spacer(modifier = Modifier.height(22.dp))
        CompraTextField(
            value = campos.value["instrucciones"] ?: "",
            onValueChange = { newValue -> campos.value += "instrucciones" to newValue },
            label = "Instrucciones"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)  // Hace que la cuadrícula ocupe el espacio disponible
            ){

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(selectedProducts) { product ->
                        CompraItem(
                            product = product
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Agregar un Text que muestre el total de la compra
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total: ${totalCompra}$",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = {
                    val direccion = buildDireccion()
                    println(direccion)
                          },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black)
            ) {
                Text(text = "Realizar pedido", fontFamily = ubuntuFont)
            }
        }

}
@Composable
fun CompraItem(product: Menu) {
    Column (
        modifier = Modifier
            .wrapContentSize()
    ){
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(
                modifier = Modifier
                    .clickable {}
                    .padding(16.dp, 4.dp, 16.dp, 4.dp)
                    .wrapContentSize() // Ajusta el tamaño al contenido

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
            }
        }

    }

}

@Composable
@Preview
fun ShowComprarScreenPreview() {
    val navController = rememberNavController()
    val selectedProducts = listOf(
        Menu(name = "Meal 1", image = R.drawable.meal_1, price = 10, quantity = 2),
        Menu(name = "Meal 2", image = R.drawable.meal_2, price = 12, quantity = 1),
    )

    ShowComprarScreen(navController = navController, selectedProducts = selectedProducts)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompraTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailing: (@Composable () -> Unit)? = null
) {
    Column {
        Text(text = label)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .shadow(3.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Red),

            ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                ),
                trailingIcon = trailing
            )
        }
    }
}