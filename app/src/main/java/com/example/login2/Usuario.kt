package com.example.login2

class Usuario (
    val nombres: String,
    val apellidos: String,
    val correo: String,
    val telefono: String,
    val usuario: String,
    val password: String
) {
    fun mostrarInformacion() {
        println("Nombres: $nombres")
        println("Apellidos: $apellidos")
        println("Correo: $correo")
        println("Telefono: $telefono")
        println("Usuario: $usuario")
    }
}