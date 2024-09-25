package com.example.login2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.login2.databinding.ActivityLoginBinding
import java.io.File
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var registerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val edtUsername = binding.edtUsername
        val edtPassword = binding.edtPassword
        val btnLogin = binding.btnLogin
        val btnRegister = binding.btnRegister

        registerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("usuario")
                //guardar user en el data.txt
                saveUser(data)
            }
        }

        //Cuando se haga click en el boton login
        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if (verificar(username, password)) {
                //entonces el usuario existe en data.txt
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext, "Error en la autenticacion", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Credenciales incorrectas o inexitentes")
            }
        }

        //click en el boton register
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            registerLauncher.launch(intent)
        }
    }

    fun verificar(username: String?, password: String?): Boolean {
        val archivo = File(filesDir, "data.txt")
        if (archivo.exists()) {
            val contenido = archivo.readText()
            val usuarios = Gson().fromJson(contenido, Array<Usuario>::class.java)
            return usuarios.any { obj -> obj.usuario == username && obj.password == password }
        }
        return false
    }

    fun saveUser(userJson: String?) {
        if (userJson != null) {
            val archivo = File(filesDir, "data.txt")
            if (!archivo.exists()) {
                archivo.writeText("[]")
            }

            val usuarios = Gson().fromJson(archivo.readText(), Array<Usuario>::class.java).toMutableList()
            val nuevoUsuario = Gson().fromJson(userJson, Usuario::class.java)
            usuarios.add(nuevoUsuario)
            archivo.writeText(Gson().toJson(usuarios))
            Toast.makeText(applicationContext, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(applicationContext, "Usuario vacio", Toast.LENGTH_SHORT).show()
        }
    }
}