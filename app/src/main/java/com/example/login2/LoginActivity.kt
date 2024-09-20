package com.example.login2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val edtUsername = binding.edtUsername
        val edtPassword = binding.edtPassword
        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener {
            if (edtUsername.text.toString() == "admin" && edtPassword.text.toString() == "admin") {
                Toast.makeText(applicationContext, "Bienvenido a mi App", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Bienvenido a mi App")
            } else {
                Toast.makeText(applicationContext, "Error en la autenticacion", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error en la autenticacion")
            }
        }

    }

    fun saveUser(context: Context, user: Usuario, fileName: String) {
        try {
            var fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
            //guardar objeto
            fileOutputStream.write((user.toString()+"\n").toByteArray())
            fileOutputStream.close()
            Toast.makeText(applicationContext, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Log.d(TAG, "Error al guardar datos")
            e.printStackTrace()
        }
    }

    fun readUsers(context: Context, filename: String) {
        try {

            var fileInputStream = context.openFileInput(filename)
            var inputStreamReader = fileInputStream.bufferedReader()

            val content = inputStreamReader.use { it.readText() }
            fileInputStream.close()
            Log.d(TAG, content)

        } catch (e: Exception) {
            Log.d(TAG, "Error al leer datos")
            e.printStackTrace()
        }
    }
}