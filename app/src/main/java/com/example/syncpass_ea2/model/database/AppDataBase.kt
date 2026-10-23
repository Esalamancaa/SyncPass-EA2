package com.example.syncpass_ea2.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.syncpass_ea2.model.dao.EventoDao
import com.example.syncpass_ea2.model.dao.ProyectoDao
import com.example.syncpass_ea2.model.dao.UsuarioDao
import com.example.syncpass_ea2.model.entities.Evento
import com.example.syncpass_ea2.model.entities.Proyecto
import com.example.syncpass_ea2.model.entities.Usuario

@Database(entities = [Proyecto::class, Evento::class, Usuario::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun proyectoDao(): ProyectoDao
    abstract fun eventoDao(): EventoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun obtenerBaseDeDatos(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "syncpass_local_db"
                )
                    .addCallback(object : RoomDatabase.Callback() {

                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            db.execSQL(
                                """
                                INSERT INTO usuarios (correo, password)
                                VALUES ('admin@syncpass.cl', 'SyncPass123')
                                """.trimIndent()
                            )
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}