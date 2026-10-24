package com.example.syncpass_ea2.navigation

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "session_syncpass"
)

class AdministradorSesion(private val context: Context) {

    companion object{
        private val sesionActiva =
        booleanPreferencesKey("sesionActiva")
    }

    suspend fun guardarSesion(){
        context.dataStore.edit { preferences ->
            preferences[sesionActiva] = true
        }
    }

    suspend fun haySesionActiva(): Boolean {
        return context.dataStore.data.first()[sesionActiva] ?: false
    }

    suspend fun cerrarSesion(){
        context.dataStore.edit { preferences ->
            preferences[sesionActiva] = false
        }
    }
}