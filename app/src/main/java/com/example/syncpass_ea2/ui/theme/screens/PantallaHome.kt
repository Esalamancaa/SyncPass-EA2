package com.example.syncpass_ea2.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaHome(navController: NavController) {
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
                text = "USUARIO",
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
                        navController.navigate("PantallaCamara")
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
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = "Pantalla de Camara",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "CONFIRMAR ASISTENTE", fontSize = 25.sp)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(400.dp)
                    .height(150.dp)
                    .clickable {
                        navController.navigate("FormularioAsistentes")
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
                        imageVector = Icons.Filled.Create,
                        contentDescription = "Registro de Asistentes",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "REGISTRAR ASISTENTE", fontSize = 25.sp)
                }
            }
        }
    }
}