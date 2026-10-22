package com.example.syncpass_ea2.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "eventos")
data class Evento (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val proyecto: Proyecto,
    val nombre: String,
    val fecha: LocalDateTime,
    val cupos: Int,
    val direccion: String,
    val gpsLatitud: Double,
    val gpsLongitud: Double

)
