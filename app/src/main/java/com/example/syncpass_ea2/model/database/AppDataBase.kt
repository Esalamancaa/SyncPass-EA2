package com.example.syncpass_ea2.model.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.syncpass_ea2.model.dao.AsistenteDao
import com.example.syncpass_ea2.model.dao.EventoDao
import com.example.syncpass_ea2.model.dao.ProyectoDao
import com.example.syncpass_ea2.model.entities.Asistente
import com.example.syncpass_ea2.model.entities.Evento
import com.example.syncpass_ea2.model.entities.Proyecto

@Database(entities = [Proyecto::class, Asistente::class, Evento::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun proyectoDao(): ProyectoDao
    abstract fun asistenteDao(): AsistenteDao
    abstract fun eventoDao(): EventoDao
    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun obtenerBaseDeDatos(context: Context): AppDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "syncpass_local_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}