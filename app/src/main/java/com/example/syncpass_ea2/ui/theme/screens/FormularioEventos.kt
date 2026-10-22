package com.example.syncpass_ea2.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formularioEvento(){

    var proyectoAsociado by remember() { mutableStateOf("") }
    var nombreEvento by remember() { mutableStateOf("") }
    var cuposMaximos by remember() { mutableStateOf("") }
    var direccion by remember() { mutableStateOf("") }
    var gpsLatitud by remember { mutableStateOf("") }
    var gpsLongitud by remember { mutableStateOf("") }

    var fechaEvento by remember { mutableStateOf("") }
    val seleccionFecha = rememberDatePickerState()
    var mostrarSeleccionFecha by remember { mutableStateOf(false) }

    var horaEvento by remember { mutableStateOf("") }
    val seleccionHora = rememberTimePickerState(initialHour = 12, initialMinute = 0)
    var mostrarSeleccionHora by remember { mutableStateOf(false) }

    if (mostrarSeleccionFecha) {
        DatePickerDialog(
            onDismissRequest = { mostrarSeleccionFecha = false },
            confirmButton = {
                TextButton(onClick = {
                    seleccionFecha.selectedDateMillis?.let { millis ->
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        sdf.timeZone = TimeZone.getTimeZone("UTC")
                        fechaEvento = sdf.format(Date(millis))
                    }
                    mostrarSeleccionFecha = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarSeleccionFecha = false }) {
                    Text("Cancelar")
                }
            }
        )
        {
            DatePicker(state = seleccionFecha)
        }
    }

    if (mostrarSeleccionHora) {
        AlertDialog(
            onDismissRequest = { mostrarSeleccionHora = false },
            confirmButton = {
                TextButton(onClick = {
                    horaEvento = String.format(Locale.getDefault(), "%02d:%02d", seleccionHora.hour, seleccionHora.minute)
                    mostrarSeleccionHora = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarSeleccionHora = false }) {
                    Text("Cancelar")
                }
            },
            text = {
                TimePicker(state = seleccionHora)
            }
        )
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("REGISTRO DE EVENTO")}
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
                value = proyectoAsociado,
                onValueChange = {proyectoAsociado = it},
                label = { Text("Proyecto Asociado") }
            )
            OutlinedTextField(
                value = nombreEvento,
                onValueChange = {nombreEvento = it},
                label = { Text("Nombre del evento") }
            )
            OutlinedTextField(
                value = fechaEvento,
                onValueChange = {},
                label = {Text("Fecha del evento")},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{mostrarSeleccionFecha = true}
            )
            OutlinedTextField(
                value = horaEvento,
                onValueChange = {},
                label = {Text("Hora del evento")},
                readOnly = true,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{mostrarSeleccionHora = true}
            )
            OutlinedTextField(
                value = cuposMaximos,
                onValueChange = {cuposMaximos = it},
                label = { Text("Cupos máximos") }
            )
            OutlinedTextField(
                value = direccion,
                onValueChange = {direccion = it},
                label = { Text("Dirección") }
            )
            OutlinedTextField(
                value = gpsLatitud,
                onValueChange = {gpsLatitud = it},
                label = { Text("Latitud gps(aproximada)") }
            )
            OutlinedTextField(
                value = gpsLongitud,
                onValueChange = {gpsLongitud = it},
                label = { Text("Longitud gps(aproximada)") }
            )
        }
    }
}