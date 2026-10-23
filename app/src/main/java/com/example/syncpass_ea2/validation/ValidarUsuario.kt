package com.example.syncpass_ea2.validation


object ValidarUsuario {

    fun validarCorreo(correo: String): String?{
        return when{
            correo.isBlank() -> "Debe ingresar un correo"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()-> "El formato del correo no es válido"
            else -> null
        }
    }

    fun validarPassword(password: String): String?{
        return when{
            password.isBlank() -> "Debe ingresar la contraseña"
            password.length < 6 -> "La contraseña no puede se menor a 6 caracteres"
            else -> null
        }
    }
}