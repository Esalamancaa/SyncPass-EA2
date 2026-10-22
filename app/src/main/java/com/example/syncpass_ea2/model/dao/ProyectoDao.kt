package com.example.syncpass_ea2.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.syncpass_ea2.model.entities.Proyecto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProyectoDao {
    @Insert
    suspend fun insertarProyecto(proyectoEntity: Proyecto)

    @Query("SELECT * FROM proyectos")
    fun obtenerProyectos(): Flow<List<Proyecto>>
}