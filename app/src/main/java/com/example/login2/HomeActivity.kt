package com.example.login2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.login2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val txtMutable = binding.txtMutable

        val username = intent.getStringExtra("user")

        txtMutable.text = username ?: "Usuario desconocido"

    }
}