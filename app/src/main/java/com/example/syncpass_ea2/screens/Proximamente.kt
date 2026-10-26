package com.example.syncpass_ea2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
fun Proximamente(
    navController: NavController,
    rutaHome: String
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "PROXIMAMENTE",
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Pantalla en desarrollo",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Será accesible en la siguiente entrega",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF2B88C4),
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate(rutaHome) {
                        popUpTo(rutaHome) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.size(
                    width = 250.dp,
                    height = 50.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B88C4),
                    contentColor = Color.White
                )
            ) {
                Text("Inicio")
            }
        }
    }
}