package com.example.syncpass_ea2.ui.theme.screens

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.syncpass_ea2.model.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioProyectos(){


    var titulo by remember() { mutableStateOf("") }
    var descripcion by remember() { mutableStateOf("") }
    var areaTematica by remember() { mutableStateOf("") }
    var unidadVecinal by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    var fechaInicio by remember { mutableStateOf("") }
    val seleccionFechaInicio = rememberDatePickerState()
    var mostrarSeleccionInicio by remember { mutableStateOf(false) }

    var fechaTermino by remember { mutableStateOf("") }
    val seleccionFechaTermino = rememberDatePickerState()
    var mostrarSeleccionTermino by remember { mutableStateOf(false) }

    if (mostrarSeleccionInicio) {
        DatePickerDialog(
            onDismissRequest = { mostrarSeleccionInicio = false },
            confirmButton = {
                TextButton(onClick = {
                    seleccionFechaInicio.selectedDateMillis?.let { millis ->
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        sdf.timeZone = TimeZone.getTimeZone("UTC")
                        fechaInicio = sdf.format(Date(millis))
                    }
                    mostrarSeleccionInicio = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarSeleccionInicio = false }) {
                    Text("Cancelar")
                }
            }
        )
        {
            DatePicker(state = seleccionFechaInicio)
        }
    }

    if (mostrarSeleccionTermino) {
        DatePickerDialog(
            onDismissRequest = { mostrarSeleccionTermino = false },
            confirmButton = {
                TextButton(onClick = {
                    seleccionFechaTermino.selectedDateMillis?.let { millis ->
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        sdf.timeZone = TimeZone.getTimeZone("UTC")
                        fechaTermino = sdf.format(Date(millis))
                    }
                    mostrarSeleccionTermino = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarSeleccionTermino = false }) {
                    Text("Cancelar")
                }
            }
        )
        {
            DatePicker(state = seleccionFechaTermino)
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("REGISTRO DE PROYECTO")}
            )

        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = {titulo = it},
                label = { Text("Titulo") }
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = {descripcion = it},
                label = { Text("Descripción") }
            )
            OutlinedTextField(
                value = areaTematica,
                onValueChange = {areaTematica = it},
                label = { Text("Area temática") }
            )
            OutlinedTextField(
                value = fechaInicio,
                onValueChange = {},
                label = {Text("Fecha de inicio")},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .clickable{mostrarSeleccionInicio = true}
            )
            OutlinedTextField(
                value = fechaTermino,
                onValueChange = {},
                label = {Text("Fecha de termino")},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .clickable{mostrarSeleccionTermino = true}
            )
            OutlinedTextField(
                value = estado,
                onValueChange = {estado = it},
                label = { Text("Estado") }
            )

            Spacer(modifier = Modifier.height(10.dp))


        }
    }
}