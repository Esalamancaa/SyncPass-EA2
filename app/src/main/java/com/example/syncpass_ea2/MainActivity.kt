package com.example.syncpass_ea2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.example.syncpass_ea2.navigation.AppNavigation
import com.example.syncpass_ea2.ui.theme.screens.formularioEvento
import com.example.syncpass_ea2.ui.theme.screens.formularioProyectos
import com.example.syncpass_ea2.ui.theme.screens.pantallaLogin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}