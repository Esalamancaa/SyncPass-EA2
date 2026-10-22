package com.example.syncpass_ea2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.syncpass_ea2.ui.theme.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "InicioSesion"
    ) {
        composable("InicioSesion") {
            pantallaLogin(navController = navController)
        }
        composable("PantallaHome") {
            pantallaHome(navController = navController)
        }
        composable("FormularioEventos"){
            formularioEvento()
        }
        composable("PantallaCamara") {
            pantallaCamara()
        }
        composable("FormularioAsistentes"){
            FormularioAsistentes()
        }
    }
}