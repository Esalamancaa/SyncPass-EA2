package com.example.syncpass_ea2.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.syncpass_ea2.navigation.AdministradorSesion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCoordinador(navController: NavController) {

    val context = LocalContext.current
    val administradorSesion = remember {
        AdministradorSesion(context)
    }
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("Usuario") }

    LaunchedEffect (Unit) {
        nombre = administradorSesion.obtenerNombreUsuario()
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "HOME",
                        color = Color.White,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                administradorSesion.cerrarSesion()

                                navController.navigate("InicioSesion") {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF2B88C4)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "BIENVENIDO A SYNCPASS,",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = nombre.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF2B88C4),
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                    .height(150.dp)
                    .clickable {
                        navController.navigate("PantallaProyectos")
                    },
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Archive,
                        contentDescription = "Proyectos inscritos",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "PROYECTOS INSCRITOS", fontSize = 25.sp)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                    .height(150.dp)
                    .clickable {
                        navController.navigate("PantallaEventos")
                    },
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.EventAvailable,
                        contentDescription = "EVENTOS INSCRITOS",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "EVENTOS INSCRITOS", fontSize = 25.sp)
                }
            }
        }
    }
}