package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mx.ipn.escom.verac.foodtruck2.R
import mx.ipn.escom.verac.foodtruck2.components.TabLayout
import mx.ipn.escom.verac.foodtruck2.ui.theme.ubuntuFont
import kotlin.collections.listOf



data class Menu(
    val name: String,
    @DrawableRes val image: Int,
    val price: Int,
    var quantity: Int
)

val foods = listOf(
    Menu(name = "pechuga con verduras", image = R.drawable.meal_1, price = 80, quantity = 0),
    Menu(name = "Comida del día", image = R.drawable.meal_2, price = 75, quantity = 0),
    Menu(name = "Albondigas", image = R.drawable.meal_3, price = 60, quantity = 0),
    Menu(name = "Filete", image = R.drawable.meal_4, price = 120, quantity = 0),
    Menu(name = "Pechuga asada", image = R.drawable.meal_5, price = 90, quantity = 0),
    Menu(name = "Pollo al vapor", image = R.drawable.meal_6, price = 80, quantity = 0),
    Menu(name = "Ensalada napolitana", image = R.drawable.sides_1, price = 70, quantity = 0),
    Menu(name = "Pasta blanca", image = R.drawable.sides_2, price = 60, quantity = 0),
    Menu(name = "Ensalada de frutas", image = R.drawable.sides_3, price = 45, quantity = 0),
    Menu(name = "Verduras al vapor", image = R.drawable.sides_4, price = 50, quantity = 0),
    Menu(name = "Papas 1", image = R.drawable.sides_5, price = 45, quantity = 0),
    Menu(name = "Papas 2", image = R.drawable.sides_6, price = 30, quantity = 0),
)

val cartItems = mutableStateListOf<Pair<Menu, Int>>()
var selectedTabIndex = mutableStateOf(0)
var selectedProducts = mutableStateListOf<Menu>()
var allProducts = mutableStateListOf(*foods.toTypedArray())
var OpcionCarrito = mutableStateOf(0)
var OpcionPedidos = mutableStateOf(0)
var pedidoDetalles = mutableStateOf<Orden?>(null)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var enableSystemBackButton by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            BackHandler(enableSystemBackButton) {
                when(selectedTabIndex.value){
                    0 -> selectedTabIndex.value = 0
                    1 -> when (OpcionCarrito.value){
                        0 -> selectedTabIndex.value = 0
                        1 -> OpcionCarrito.value = 0
                    }
                    2 -> when (OpcionPedidos.value){
                        0 -> selectedTabIndex.value = 0
                        1 -> OpcionPedidos.value = 0
                    }
                }
            }

            CenterAlignedTopAppBar(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                title = { Text("Bienvenido", fontFamily = ubuntuFont) },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {
                            when(selectedTabIndex.value){
                                0 -> selectedTabIndex.value = 0
                                1 -> when (OpcionCarrito.value){
                                    0 -> selectedTabIndex.value = 0
                                    1 -> OpcionCarrito.value = 0
                                }
                                2 -> when (OpcionPedidos.value){
                                    0 -> selectedTabIndex.value = 0
                                    1 -> OpcionPedidos.value = 0
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                        }

                    }
                }
            )
            // Contenido de la barra superior con el TabLayout
                Column(
                    modifier = Modifier.padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TabLayout(
                        selectedIndex = selectedTabIndex,
                        items = listOf(
                            "Menu" to { Foods(navController = navController) },
                            "Carrito" to { if(OpcionCarrito.value == 0){
                                ShowCartScreen(navController = navController, selectedProducts = selectedProducts.toList())}
                            else{
                                ShowComprarScreen(navController = navController, selectedProducts.toList())
                            } },
                            "Pedidos" to { if(OpcionPedidos.value == 0){
                                ShowOrdersTab(navController = navController)}
                            else{
                                DetallesOrden (navController = navController, pedidoDetalles = pedidoDetalles.value)
                            }  }
                        ),
                        onTabClick = { selectedTabIndex.value = it }
                    )
                }

        }
    ) { innerPaddings ->
        Box(Modifier.padding(innerPaddings)) {
            when (selectedTabIndex.value) {
                0 -> Foods(navController = navController)
                1 -> {
                    if(OpcionCarrito.value == 0){
                        ShowOrdersTab(navController = navController)}
                    else{
                        ShowComprarScreen(navController = navController, selectedProducts.toList())
                    }
                }
                2 -> {
                    if(OpcionCarrito.value == 0){
                        ShowCartScreen(navController = navController, selectedProducts = selectedProducts.toList())}
                    else{
                        DetallesOrden (navController = navController, pedidoDetalles = pedidoDetalles.value)
                    }
                }
            }
        }
    }
}


@Composable
fun Foods(navController: NavController) {
    val foodsState = remember {
        mutableStateListOf(*foods.toTypedArray())
    }
    var items by remember { mutableStateOf(allProducts) }

    var onAddToCartClick = { food: Menu ->
        // Lógica para añadir al carrito con nombre y cantidad
        selectedProducts.add(food)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff1f1f1))
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Primera Columna para la cuadrícula de productos
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items) { index, food ->
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Image(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                painter = painterResource(id = food.image),
                                contentDescription = food.name,
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = food.name, fontSize = 15.sp, color = Color(0xff383838))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "${food.price}$")
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                var quantity by remember { mutableStateOf(food.quantity) }
                                // Botón de disminuir cantidad
                                IconButton(
                                    onClick = {
                                        if (quantity > 0) {
                                            quantity--
                                        }
                                    }
                                ) {
                                    Icon(Icons.Rounded.Build, contentDescription = "Disminuir cantidad")
                                }

                                // Contador de cantidad
                                Text(
                                    text = quantity.toString(),
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 18.sp
                                )

                                // Botón de aumentar cantidad
                                IconButton(
                                    onClick = {
                                        quantity++
                                    }
                                ) {
                                    Icon(Icons.Rounded.Add, contentDescription = "Aumentar cantidad")
                                }
                                DisposableEffect(quantity) {
                                    onDispose {
                                        food.quantity = quantity

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Spacer para dar espacio entre la cuadrícula y el botón
        Spacer(modifier = Modifier.height(16.dp))

        // Segunda Columna para el botón de agregar al carrito
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Button(
                onClick = {
                    // Lógica para agregar al carrito
                    for (product in items) {
                        if (product.quantity > 0) {
                            val existingProduct = selectedProducts.find { it.name == product.name }
                            if (existingProduct != null) {
                                existingProduct.quantity = product.quantity
                            } else {
                                selectedProducts.add(product.copy())
                            }
                        }
                    }
                    if (selectedProducts.isNotEmpty()) {
                        selectedTabIndex.value = 1
                        allProducts = mutableStateListOf(*items.toTypedArray())
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Agregar al Carrito")
            }
        }
    }
}

