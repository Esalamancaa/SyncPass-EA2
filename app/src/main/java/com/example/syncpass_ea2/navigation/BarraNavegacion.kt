package com.example.syncpass_ea2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

private data class ItemNavbar(
    val ruta: String,
    val titulo: String,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun BarraNavegacion(navController: NavController, rutaActual: String) {
    val items = listOf(
        ItemNavbar("PantallaHome", "Inicio", Icons.Filled.Home),
        ItemNavbar("FormularioEventos", "Registro", Icons.Filled.Event),
        ItemNavbar("PantallaCamara", "Cámara", Icons.Filled.PhotoCamera),
        ItemNavbar("FormularioAsistentes", "Asistentes", Icons.Filled.People)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = rutaActual == item.ruta,
                onClick = {
                    if (rutaActual != item.ruta) {
                        navController.navigate(item.ruta) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo("PantallaHome") {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icono,
                        contentDescription = item.titulo
                    )
                },
                label = { Text(item.titulo) }
            )
        }
    }
}