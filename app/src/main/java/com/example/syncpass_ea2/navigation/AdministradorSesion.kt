package com.example.syncpass_ea2.navigation

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "session_syncpass"
)

class AdministradorSesion(private val context: Context) {

    companion object {
        private val sesionActiva =
            booleanPreferencesKey("sesionActiva")

        private val nombreUsuario =
            stringPreferencesKey("nombre_usuario")

        private val rolUsuario =
            stringPreferencesKey("rol_usuario")
    }

    suspend fun guardarSesion(nombre: String, rol: String) {
        context.dataStore.edit { preferences ->
            preferences[sesionActiva] = true
            preferences[nombreUsuario] = nombre
            preferences[rolUsuario] = rol
        }
    }

    suspend fun haySesionActiva(): Boolean {
        return context.dataStore.data.first()[sesionActiva] ?: false
    }

    suspend fun obtenerNombreUsuario(): String {
        return context.dataStore.data.first()[nombreUsuario] ?: "Usuario"
    }

    suspend fun obtenerRolUsuario(): String {
        return context.dataStore.data.first()[rolUsuario] ?: ""
    }

    suspend fun cerrarSesion() {
        context.dataStore.edit { preferences ->
            preferences[sesionActiva] = false
            preferences.remove(nombreUsuario)
            preferences.remove(rolUsuario)
        }
    }
}