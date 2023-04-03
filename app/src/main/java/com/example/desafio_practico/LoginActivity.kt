package com.example.desafio_practico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIniciarSesion = findViewById<Button>(R.id.IniciarSesion)
        val btnCrearCuenta = findViewById<Button>(R.id.CrearCuenta)

        btnIniciarSesion.setOnClickListener {
            val correo = findViewById<EditText>(R.id.correo)
            val contrasena = findViewById<EditText>(R.id.contrasena)




            iniciarSesion(correo!!.text.toString(), contrasena!!.text.toString())
        }

        btnCrearCuenta.setOnClickListener {
            val correo = findViewById<EditText>(R.id.correo)
            val contrasena = findViewById<EditText>(R.id.contrasena)
            crearCuenta(correo!!.text.toString(), contrasena!!.text.toString())
        }


    }


    //cotrolador


    private fun iniciarSesion(correo: String, contrasena: String) {
        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signInWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { funciono ->
                if (funciono.isSuccessful) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Toast.makeText(this, "Bienvenido $correo", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, "Vuelve a intentar", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun crearCuenta(correo: String, contrasena: String) {

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener { funciono ->
                if (funciono.isSuccessful) {
                    Toast.makeText(this, "Inicie sesion, su cuenta fue creada", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Vuelve a intentar", Toast.LENGTH_SHORT).show()

                }
            }

    }


}

