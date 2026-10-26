package com.example.syncpass_ea2.model.dao

import com.example.syncpass_ea2.model.entities.Usuario
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrarUsuario(user: Usuario)

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND password = :password LIMIT 1")
    suspend fun login(correo: String, password: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun buscarCorreo(correo: String): Usuario?
}
