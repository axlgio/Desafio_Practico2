package com.example.desafio_practico.Promedio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.desafio_practico.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddPromedioActivity : AppCompatActivity() {


    private lateinit var edtNombre: EditText
    private lateinit var edtN1: EditText
    private lateinit var edtN2: EditText
    private lateinit var edtN3: EditText
    private lateinit var edtN4: EditText
    private lateinit var edtN5: EditText

    private var key = ""
    private var studiante = ""
    private var n1 = 0.0
    private var n2 = 0.0
    private var n3 = 0.0
    private var n4 = 0.0
    private var n5 = 0.0
    private var accion = ""
    private lateinit var  database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_promedio)
        inicializar()
    }

    private fun inicializar() {
        edtNombre = findViewById<EditText>(R.id.et_nombre)
        edtN1 = findViewById<EditText>(R.id.et_nota1)
        edtN2 = findViewById<EditText>(R.id.et_nota2)
        edtN3 = findViewById<EditText>(R.id.et_nota3)
        edtN4 = findViewById<EditText>(R.id.et_nota4)
        edtN5 = findViewById<EditText>(R.id.et_nota5)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            key = datos.getString("key").toString()
        }
        if (datos != null) {
            edtN1.setText(intent.getDoubleExtra("n1", 0.0).toString())
            edtN2.setText(intent.getDoubleExtra("n2", 0.0).toString())
            edtN3.setText(intent.getDoubleExtra("n3", 0.0).toString())
            edtN4.setText(intent.getDoubleExtra("n4", 0.0).toString())
            edtN5.setText(intent.getDoubleExtra("n5", 0.0).toString())
        }
        if (datos != null) {
            edtNombre.setText(intent.getStringExtra("studiante").toString())
        }
        if (datos != null) {
            accion = datos.getString("accion").toString()
        }

    }


    fun guardar(v: View?) {

        val estudiante: String = edtNombre?.text.toString()

        val n1: Double = edtN1?.text.toString().toDouble()
        val n2: Double = edtN2?.text.toString().toDouble()
        val n3: Double = edtN3?.text.toString().toDouble()
        val n4: Double = edtN4?.text.toString().toDouble()
        val n5: Double = edtN5?.text.toString().toDouble()

        database= FirebaseDatabase.getInstance().getReference("promedio")

        // Se forma objeto persona

        val promedio = Pro(estudiante, n1, n2, n3, n4, n5)
        if (accion == "a") { //Agregar registro
            database.child(estudiante).setValue(promedio).addOnSuccessListener {
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
            val promedioValue = promedio.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$estudiante" to promedioValue
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