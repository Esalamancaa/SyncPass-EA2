package com.example.syncpass_ea2.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.syncpass_ea2.R
import com.example.syncpass_ea2.model.dao.UsuarioDao
import kotlinx.coroutines.launch

@Composable
fun pantallaLogin(navController: NavController, usuarioDao: UsuarioDao) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.sync_letras_logo),
            contentDescription = "Logo de aplicación en Login",
            modifier = Modifier.size(350.dp, 350.dp)
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo", fontSize = 20.sp) },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
                .padding(bottom = 5.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2B88C4),
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", fontSize = 20.sp) },
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
                .padding(bottom = 5.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2B88C4),
                unfocusedBorderColor = Color.Black,
                errorBorderColor = Color.Red
            )
        )

        // Mostrar el mensaje de error si existe
        if (mensajeError != null) {
            Text(
                text = mensajeError!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(14.dp))
        }

        Button(
            onClick = {
                if (correo.isBlank() || password.isBlank()) {
                    mensajeError = "Complete los campos faltantes"
                    return@Button
                }

                coroutineScope.launch {
                    val usuarioValido = usuarioDao.login(correo, password)

                    if (usuarioValido != null) {
                        mensajeError = null
                        navController.navigate("PantallaHome") {
                            popUpTo("pantallaLogin") { inclusive = true }
                        }
                    } else {
                        mensajeError = "Correo o contraseña inválido"
                    }
                }
            },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2B88C4),
                contentColor = Color.White
            )
        ) {
            Text(
                "Iniciar Sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}