package com.example.syncpass_ea2.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.syncpass_ea2.model.entities.Evento
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDao {
    @Insert
    suspend fun insertarEvento(evento: Evento)

    @Query("SELECT * FROM eventos")
    fun obtenerProyectos(): Flow<List<Evento>>
}