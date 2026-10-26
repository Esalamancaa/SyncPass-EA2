package com.example.syncpass_ea2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

private data class ItemNavbarCoordinador(
    val ruta: String,
    val titulo: String,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun BarraNavCoordinador(
    navController: NavController,
    rutaActual: String,
    rutaHome: String
) {
    val items = listOf(
        ItemNavbarCoordinador(rutaHome, "Inicio", Icons.Filled.Home),
        ItemNavbarCoordinador("PantallaRegistros", "Registro", Icons.Filled.Event),
        ItemNavbarCoordinador("PantallaInformes", "Informes", Icons.Filled.AutoStories)
    )

    NavigationBar(
        containerColor = Color(0xFF2B88C4)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = rutaActual == item.ruta,
                onClick = {
                    if (rutaActual != item.ruta) {
                        navController.navigate(item.ruta) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(rutaHome) {
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
                label = { Text(item.titulo) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color.White
                )
            )
        }
    }
}