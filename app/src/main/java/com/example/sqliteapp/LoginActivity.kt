package com.example.sqliteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteapp.model.Usuario

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarioModel: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioModel = Usuario(this)

        val txtNick = findViewById<EditText>(R.id.txtNick)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnLogin.setOnClickListener {
            val nick = txtNick.text.toString()
            val pass = txtPassword.text.toString()

            if (nick.isNotEmpty() && pass.isNotEmpty()) {
                if (usuarioModel.validarLogin(nick, pass)) {
                    Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistrar.setOnClickListener {
            val nick = txtNick.text.toString()
            val pass = txtPassword.text.toString()

            if (nick.isNotEmpty() && pass.isNotEmpty()) {
                val resultado = usuarioModel.registrarUsuario(nick, pass)
                if (resultado != -1L) {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese nick y contraseña para registrarse", Toast.LENGTH_SHORT).show()
            }
        }
    }
}