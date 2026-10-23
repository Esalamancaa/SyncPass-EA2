package com.example.syncpass_ea2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.syncpass_ea2.model.database.AppDataBase
import com.example.syncpass_ea2.ui.theme.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    val mostrarNavbar = rutaActual in listOf(
        "PantallaHome",
        "FormularioEventos",
        "PantallaCamara",
        "FormularioAsistentes"
    )

    Scaffold(
        bottomBar = {
            if (mostrarNavbar) {
                BarraNavegacion(
                    navController = navController,
                    rutaActual = rutaActual.orEmpty()
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "InicioSesion",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("InicioSesion") {
                val context = LocalContext.current
                val database = AppDataBase.obtenerBaseDeDatos(context)

                pantallaLogin(
                    navController = navController,
                    usuarioDao = database.usuarioDao()
                )
            }

            composable("PantallaHome") {
                pantallaHome(navController = navController)
            }

            composable("FormularioEventos") {
                formularioEvento()
            }

            composable("PantallaCamara") {
                pantallaCamara()
            }

            composable("FormularioAsistentes") {
                FormularioAsistentes()
            }
        }
    }
}