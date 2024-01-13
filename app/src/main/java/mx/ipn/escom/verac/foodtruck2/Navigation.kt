package mx.ipn.escom.verac.foodtruck2

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.ipn.escom.verac.foodtruck2.Screens.AuthScreen
import mx.ipn.escom.verac.foodtruck2.Screens.HomeScreen
import mx.ipn.escom.verac.foodtruck2.Screens.MenuScreen
import mx.ipn.escom.verac.foodtruck2.Screens.ShowCartScreen
import mx.ipn.escom.verac.foodtruck2.Screens.cartItems

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPreferences.getBoolean("loggedIn",false)
    NavHost(navController = navController, startDestination = if (userLoggedIn) "home" else "auth"){
        composable("auth"){
            AuthScreen(navController = navController)
        }
        composable("home"){
            HomeScreen(navController = navController)
        }

        composable("menu"){
            MenuScreen(navController = navController)
        }


    }
}