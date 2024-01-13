package mx.ipn.escom.verac.foodtruck2.Screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.R
import mx.ipn.escom.verac.foodtruck2.components.TabLayout
import mx.ipn.escom.verac.foodtruck2.ui.theme.ubuntuFont

@Composable
fun AuthScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val selectedTab = remember {
            mutableIntStateOf(0)
        }
        TabLayout(
            selectedIndex = selectedTab,
            items = listOf(
                "Iniciar sesión" to {
                    SignIn(
                        navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                },
                "Registro" to {
                    SignUp(
                        navController = navController,
                        sharedPreferences = sharedPreferences
                    )
                }
            ),
            onTabClick = {
                selectedTab.intValue = it
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }

    val radioOptions = listOf("Cliente", "Empresa")
    var selectedRadioOption by remember { mutableStateOf(radioOptions[0]) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        AppTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = "Correo electrónico"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            label = "Contraseña",
            visualTransformation = if (showPassword.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailing = {
                Icon(
                    modifier = Modifier.clickable {
                        showPassword.value = !showPassword.value
                    },
                    painter = painterResource(
                        id = if (showPassword.value)
                            R.drawable.ic_eye_off else R.drawable.ic_eye_open
                    ),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "¿Olvidaste tu contraseña?", color = Color.Gray, fontSize = 11.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))
        RadioGroup(
            options = radioOptions,
            selectedOption = selectedRadioOption,
            onOptionSelected = { option ->
                selectedRadioOption = option
            } )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                sharedPreferences.edit().apply {
                    putBoolean("loggedIn", true)
                    putString("email", email.value)
                    putString("userType", selectedRadioOption)

                }
                    .apply()
                navController.navigate("home") {
                    popUpTo(0)
                }
            }, shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Iniciar sesión", fontFamily = ubuntuFont)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    navController: NavController,
    sharedPreferences: SharedPreferences
) {
    val rememberMeChecked = remember {
        mutableStateOf(false)
    }

    val nombre = remember {
        mutableStateOf("")
    }

    val apellidos = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordRepeat = remember {
        mutableStateOf("")
    }
    val showPassword = remember {
        mutableStateOf(false)
    }
    val showPasswordRepeat = remember {
        mutableStateOf(false)
    }

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Spacer(modifier = Modifier.height(22.dp))
            AppTextField(
                value = nombre.value,
                onValueChange = {
                    nombre.value = it
                },
                label = "Nombre(s)"
            )

            Spacer(modifier = Modifier.height(22.dp))
            AppTextField(
                value = apellidos.value,
                onValueChange = {
                    apellidos.value = it
                },
                label = "Apellidos"
            )

            Spacer(modifier = Modifier.height(22.dp))
            AppTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                label = "Correo electrónico"
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = "Contaseña",
                visualTransformation = if (showPassword.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailing = {
                    Icon(
                        modifier = Modifier.clickable {
                            showPassword.value = !showPassword.value
                        },
                        painter = painterResource(
                            id = if (showPassword.value)
                                R.drawable.ic_eye_off else R.drawable.ic_eye_open
                        ),
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AppTextField(
                value = passwordRepeat.value,
                onValueChange = {
                    passwordRepeat.value = it
                },
                label = "Repite tu contraseña",
                visualTransformation = if (showPasswordRepeat.value)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailing = {
                    Icon(
                        modifier = Modifier.clickable {
                            showPasswordRepeat.value = !showPasswordRepeat.value
                        },
                        painter = painterResource(
                            id = if (showPasswordRepeat.value)
                                R.drawable.ic_eye_off else R.drawable.ic_eye_open
                        ),
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = {
                    sharedPreferences.edit().apply {
                        putBoolean("loggedIn", true)
                        putString("nombre", email.value)
                        putString("apellidos", email.value)
                        putString("email", email.value)

                    }
                        .apply()
                    navController.navigate("home") {
                        popUpTo(0)
                    }
                }, shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Registro", fontFamily = ubuntuFont)
            }}

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
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
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = trailing
            )
        }
    }
}

@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(
    ) {
        options.forEach { option ->
            RadioRow(
                text = option,
                isSelected = option == selectedOption,
                onSelected = { onOptionSelected(option) }
            )
        }
    }
}

@Composable
fun RadioRow(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(30.dp)
            .clickable { onSelected() },
        verticalAlignment = Alignment.CenterVertically,


        ) {
        RadioButton(
            selected = isSelected,
            onClick = {onSelected()} // null disables interaction
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text)
    }
}

