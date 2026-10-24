package com.example.syncpass_ea2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.syncpass_ea2.model.database.AppDataBase
import androidx.compose.runtime.setValue
import com.example.syncpass_ea2.screens.Encuesta
import com.example.syncpass_ea2.screens.FormularioAsistentes
import com.example.syncpass_ea2.screens.PantallaRegistros
import com.example.syncpass_ea2.screens.Proximamente
import com.example.syncpass_ea2.screens.formularioEvento
import com.example.syncpass_ea2.screens.formularioProyectos
import com.example.syncpass_ea2.screens.pantallaCamara
import com.example.syncpass_ea2.screens.pantallaHome
import com.example.syncpass_ea2.screens.pantallaLogin

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    val mostrarNavbar = rutaActual in listOf(
        "PantallaHome",
        "FormularioEventos",
        "PantallaCamara",
        "FormularioAsistentes",
        "PantallaRegistros",
        "Encuesta"
    )

    val context = LocalContext.current
    val administradorSesion = remember { AdministradorSesion(context) }
    var rutaInicial by remember { mutableStateOf<String?>(null) }



    LaunchedEffect(Unit) {
        rutaInicial = if (administradorSesion.haySesionActiva()){
            "PantallaHome"
        }else{
            "InicioSesion"
        }
    }

    val destino: String? = rutaInicial

    if(destino != null) {
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
                startDestination = destino,
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
                    formularioEvento(navController)
                }

                composable("PantallaCamara") {
                    pantallaCamara()
                }

                composable("FormularioAsistentes") {
                    FormularioAsistentes(navController)
                }

                composable("FormularioProyectos") {
                    formularioProyectos(navController)
                }

                composable("PantallaRegistros") {
                    PantallaRegistros(navController)
                }

                composable("Proximamente") {
                    Proximamente(navController)
                }

                composable(route = "Encuesta") {
                    var valoracionActual by remember { mutableStateOf(0) }

                    Encuesta(
                        navController = navController,
                        valoracion = valoracionActual,
                        onCambioValoracion = { nuevaValoracion ->
                            valoracionActual = nuevaValoracion
                        }
                    )
                }
            }
        }
    }
}