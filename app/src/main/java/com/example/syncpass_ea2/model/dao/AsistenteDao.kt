package com.example.syncpass_ea2.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.syncpass_ea2.model.entities.Asistente
import kotlinx.coroutines.flow.Flow

@Dao
interface AsistenteDao{
    @Insert
    suspend fun insertarAsistente(asistente: Asistente)

    @Query("SELECT * FROM asistentes")
    fun obtenerAsistentes(): Flow<List<Asistente>>
}