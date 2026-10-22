package com.example.syncpass_ea2.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.syncpass_ea2.model.entities.Asistente
import kotlinx.coroutines.flow.Flow

@Dao
interface AsistenteDao{
    @Insert
    suspend fun insertarEvento(proyectoEntity: Asistente)

    @Query("SELECT * FROM proyectos")
    fun obtenerProyectos(): Flow<List<Asistente>>
}