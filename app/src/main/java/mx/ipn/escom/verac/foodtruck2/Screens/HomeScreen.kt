package mx.ipn.escom.verac.foodtruck2.Screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import mx.ipn.escom.verac.foodtruck2.R
import mx.ipn.escom.verac.foodtruck2.components.TabLayout
import mx.ipn.escom.verac.foodtruck2.ui.theme.ubuntuFont
import androidx.compose.ui.platform.LocalContext


data class Menu(
    val name: String,
    @DrawableRes val image: Int,
    val price: Int,
    val quantity: Int
)

val foods = listOf(
    Menu(name = "Meal 1", image = R.drawable.meal_1, price = 0, quantity = 0),
    Menu(name = "Meal 2", image = R.drawable.meal_2, price = 0, quantity = 0),
    Menu(name = "Meal 3", image = R.drawable.meal_3, price = 0, quantity = 0),
    Menu(name = "Meal 4", image = R.drawable.meal_4, price = 0, quantity = 0),
    Menu(name = "Meal 5", image = R.drawable.meal_5, price = 0, quantity = 0),
    Menu(name = "Meal 6", image = R.drawable.meal_6, price = 0, quantity = 0),
    Menu(name = "Side 1", image = R.drawable.sides_1, price = 0, quantity = 0),
    Menu(name = "Side 2", image = R.drawable.sides_2, price = 0, quantity = 0),
    Menu(name = "Side 3", image = R.drawable.sides_3, price = 0, quantity = 0),
    Menu(name = "Side 4", image = R.drawable.sides_4, price = 0, quantity = 0),
    Menu(name = "Side 5", image = R.drawable.sides_5, price = 0, quantity = 0),
    Menu(name = "Side 6", image = R.drawable.sides_6, price = 0, quantity = 0),
    Menu(name = "Snack 1", image = R.drawable.snacks_1, price = 0, quantity = 0),
    Menu(name = "Snack 2", image = R.drawable.snacks_2, price = 0, quantity = 0),
    Menu(name = "Snack 3", image = R.drawable.snacks_3, price = 0, quantity = 0),
    Menu(name = "Snack 4", image = R.drawable.snacks_4, price = 0, quantity = 0),
    Menu(name = "Snack 5", image = R.drawable.snacks_5, price = 0, quantity = 0),
    Menu(name = "Snack 6", image = R.drawable.snacks_6, price = 0, quantity = 0),
)

val cartItems = mutableStateListOf<Pair<Menu, Int>>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    val selectedProducts = remember { mutableStateListOf<Pair<Menu, Int>>() }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Nuestro Menú", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
            }
        )
    }) { paddings ->
        Column() {
            Column(modifier = Modifier.padding(paddings)) {
                val selectedFoodType = remember {
                    mutableIntStateOf(0)
                }
                val foodsState = remember {
                    mutableStateListOf(*foods.toTypedArray())
                }
                Spacer(modifier = Modifier.height(16.dp))
                TabLayout(
                    items = listOf(
                        "Bebidas" to {
                            Foods(
                                navController = navController,
                                items = foodsState,
                                onTap = {
                                    navController.navigate("food")
                                },
                                onAddToCartClick = { food, quantity ->
                                    // Lógica para añadir al carrito con nombre y cantidad
                                    selectedProducts.add(Pair(food, quantity))
                                }
                            )
                        },
                        "Carrito" to {
                                navController.navigate("cart")

                        },
                        "Pedidos" to {

                        },
                    ),
                    selectedIndex = selectedFoodType.intValue,
                    onTabClick = {
                        selectedFoodType.intValue = it
                    },
                    textHeight = 30.dp,
                    indicatorPadding = PaddingValues(horizontal = 10.dp)
                )

            }

        }


    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Foods(navController: NavController, items: List<Menu>, onTap: (Menu) -> Unit, onAddToCartClick: (Menu, Int) -> Unit ) {

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
                        onClick = {
                            onTap(food)
                        },
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
                                var quantity by remember { mutableStateOf(0) }
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
                    val selectedProducts = items.filter { it.quantity > 0 }
                    for (product in selectedProducts) {
                        onAddToCartClick(product, product.quantity)
                    }
                    navController.navigate("cart") {}


                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Agregar al Carrito")
            }
        }
    }
}

