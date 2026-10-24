package com.example.syncpass_ea2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun Encuesta(navController: NavController,
             valoracion: Int,
             onCambioValoracion: (Int) -> Unit,
             modifier: Modifier = Modifier,
             maxEstrellas: Int = 5)
    {
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "ENCUESTA",
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "¡Comparte tu opinión con nosotros para seguir mejorando!",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Calidad del evento",
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (estrella in 1..maxEstrellas){
                    IconButton(
                        onClick = {onCambioValoracion(estrella)}
                    ) {
                        Icon(
                            imageVector = if ( estrella <= valoracion){
                                Icons.Filled.Star
                            }else{
                                Icons.Outlined.Star
                            },
                            "$estrella de $maxEstrellas estrellas",
                            tint = if (estrella <= valoracion){
                                Color.Black
                            }else{
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }

        }
    }
}