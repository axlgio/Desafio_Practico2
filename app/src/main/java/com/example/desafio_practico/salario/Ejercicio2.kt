package com.example.desafio_practico.salario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.desafio_practico.Promedio.Ejercicio1
import com.example.desafio_practico.Promedio.PromedioActivity
import com.example.desafio_practico.R
import com.example.desafio_practico.calculadora.Ejercicio3

class Ejercicio2 : AppCompatActivity(), Iview {

    var vistaRespuesta: TextView? = null
    var boton: Button? = null
    var fCancular: Icontrolador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        val salario = findViewById<EditText>(R.id.et_salario)

        val nombre = findViewById<EditText>(R.id.et_nombre)
        boton = findViewById(R.id.btn_calcular)

        boton = findViewById(R.id.btn_calcular)
        vistaRespuesta = findViewById(R.id.tv_salario)

        fCancular = Controlador(this)

        boton?.setOnClickListener {
            (fCancular as Controlador).saldoNeto(
                salario.text.toString().toDouble(),
                nombre.text.toString()
            )
        }

    }

    override fun resultado(resultado: String?) {
        vistaRespuesta?.text = resultado
    }

    //mando a llamar el menu opcion al main activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Navegar entre las actividades
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val intent1:Intent = Intent( this, PromedioActivity::class.java)
        val intent2:Intent = Intent( this, SalarioActivity::class.java)
        val intent3:Intent = Intent( this, Ejercicio3::class.java)

        when (item.itemId) {
            R.id.ejercicio1 -> startActivity(intent1)
            R.id.ejercicio2 -> startActivity(intent2)
            R.id.ejercicio3 -> startActivity(intent3)
        }


        return super.onOptionsItemSelected(item)
    }
}