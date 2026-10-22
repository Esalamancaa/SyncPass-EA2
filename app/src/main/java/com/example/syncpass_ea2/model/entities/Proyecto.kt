package com.example.syncpass_ea2.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "proyectos")
data class Proyecto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val areaTematica: String,
    val unidadVecinal: String,
    val encargado: String,
    val fechaInicio: LocalDateTime,
    val fechaTermino: LocalDateTime,
    val estado: Boolean
)


