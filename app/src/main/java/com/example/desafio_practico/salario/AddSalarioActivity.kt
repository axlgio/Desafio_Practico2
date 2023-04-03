package com.example.desafio_practico.salario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.desafio_practico.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddSalarioActivity : AppCompatActivity() {


    private lateinit var et_nombre : EditText
    private lateinit var et_salario : EditText

    private var key = ""
    private var nombre = ""
    private var salario = 0.0
    private var accion = ""
    private lateinit var  database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_salario)
        inicializar()
    }

    private fun inicializar() {
        et_nombre = findViewById<EditText>(R.id.et_nombre)
        et_salario = findViewById<EditText>(R.id.et_salario)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }
        if (datos != null) {
            et_salario.setText(intent.getDoubleExtra("salario", 0.0).toString())
        }
        if (datos != null) {
            et_nombre.setText(intent.getStringExtra("nombre").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }


    fun guardar(v: View?) {

        val nombre: String = et_nombre?.text.toString()

        val salario: Double = et_salario?.text.toString().toDouble()

        database= FirebaseDatabase.getInstance().getReference("salario")

        // Se forma objeto persona

        val sal = Salario(nombre, salario)
        if (accion == "a") { //Agregar registro
            database.child(nombre).setValue(sal).addOnSuccessListener {
                Toast.makeText(this,"Se guardo con exito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed ", Toast.LENGTH_SHORT).show()
            }
        } else  // Editar registro
        {
            val key = database.child("nombre").push().key
            if (key == null) {
                Toast.makeText(this,"Llave vacia", Toast.LENGTH_SHORT).show()
            }
            val salarioValue = sal.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to salarioValue
            )
            database.updateChildren(childUpdates)
            Toast.makeText(this,"Se actualizo con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    fun cancelar(v: View?) {
        finish()
    }

}