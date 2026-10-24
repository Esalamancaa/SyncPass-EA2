package com.example.syncpass_ea2.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistentes")
data class Asistente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_asistente: String,
    val tipoParticipante: String,
    val estadoInscripcion: String
)