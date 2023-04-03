package com.example.desafio_practico.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.desafio_practico.Promedio.Ejercicio1
import com.example.desafio_practico.Promedio.PromedioActivity
import com.example.desafio_practico.R
import com.example.desafio_practico.salario.Ejercicio2
import com.example.desafio_practico.salario.SalarioActivity

class Ejercicio3 : AppCompatActivity(), Iview {

    var vistaRespuesta: TextView? = null
    var boton: Button? = null
    var fCancular: Icontrolador? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        val a = findViewById<EditText>(R.id.et_valor1)
        val b = findViewById<EditText>(R.id.et_valor2)
        val spinner = findViewById<Spinner>(R.id.spinner)
        boton = findViewById(R.id.btn_calcular)
        vistaRespuesta = findViewById(R.id.tv_resultado)

        fCancular = Controlador(this)


        val lista = arrayOf("Sumar", "Restar", "Multiplicar", "Dividir")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador1


        boton?.setOnClickListener {
            (fCancular as Controlador).calculadora(
                a.text.toString().toDouble(),
                b.text.toString().toDouble(),
                spinner.selectedItem.toString()
            )
        }

    }

    override fun resultado(resultado: String?) {
        vistaRespuesta?.text = resultado
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

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