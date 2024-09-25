package com.example.login2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.login2.databinding.ActivityRegisterBinding
import com.google.gson.Gson

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val edtNombres = binding.edtNombres
        val edtApellidos = binding.edtApellidos
        val edtCorreo = binding.edtCorreo
        val edtTelefono = binding.edtTelefono
        val edtUsuario = binding.edtUsuario
        val edtNewPassword = binding.edtNewPassword
        val btnAceptar = binding.btnAceptar
        val btnCancelar = binding.btnCancelar

        btnAceptar.setOnClickListener {
            val nombres = edtNombres.text.toString()
            val apellidos = edtApellidos.text.toString()
            val correo = edtCorreo.text.toString()
            val telefono = edtTelefono.text.toString()
            val usuario = edtUsuario.text.toString()
            val password = edtNewPassword.text.toString()

            val nuevoUsuario = Usuario(nombres, apellidos, correo, telefono, usuario, password)

            val resultIntent = Intent()
            val usuarioJson = Gson().toJson(nuevoUsuario)
            resultIntent.putExtra("usuario", usuarioJson)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        btnCancelar.setOnClickListener {
            finish()
        }

    }
}