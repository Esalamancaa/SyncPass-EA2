package com.example.syncpass_ea2.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.ui.platform.LocalContext
import com.example.syncpass_ea2.model.entities.Usuario
import com.example.syncpass_ea2.navigation.AdministradorSesion

@Composable
fun PantallaRegistro(navController: NavController, usuarioDao: UsuarioDao) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmacion by remember { mutableStateOf("") }
    var tipoUsuario by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val administradorSesion = remember { AdministradorSesion(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(horizontal = 16.dp),
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
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre", fontSize = 20.sp, color = Color.Black) },
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
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo", fontSize = 20.sp, color = Color.Black) },
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

        val options = listOf("Coordinador", "Monitor", "Asistente")

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .size(width = 300.dp, height = 70.dp)
                .padding(bottom = 5.dp)
        ) {
            options.forEachIndexed { index, option ->
                val seleccionado = tipoUsuario == option

                SegmentedButton(
                    selected = seleccionado,
                    onClick = { tipoUsuario = option },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = Color(0xFF2B88C4),
                        activeContentColor = Color.White,
                        activeBorderColor = Color(0xFF2B88C4),
                        inactiveContainerColor = Color.Transparent,
                        inactiveContentColor = Color.Black,
                        inactiveBorderColor = Color.Black
                    )
                ) {
                    Text(
                        text = option,
                        fontSize = 12.sp
                    )
                }
            }
        }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", fontSize = 20.sp, color = Color.Black) },
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

        OutlinedTextField(
            value = passwordConfirmacion,
            onValueChange = { passwordConfirmacion = it },
            label = { Text("Repita contraseña", fontSize = 20.sp, color = Color.Black) },
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
                coroutineScope.launch {
                    try {
                        val existe = usuarioDao.buscarCorreo(correo)

                        if(existe != null){
                            mensajeError = "El correo ya se encuentra registrado"
                            return@launch
                        }

                        val usuario = Usuario(
                            nombre = nombre.trim(),
                            correo = correo.trim(),
                            password = password,
                            tipoUsuario = tipoUsuario
                        )

                        usuarioDao.registrarUsuario(usuario)

                        mensajeError = null

                        navController.navigate("InicioSesion")
                    }catch (e: Exception){
                        mensajeError = "No se pudo registrar al usuario"
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
                "Registrarse",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}