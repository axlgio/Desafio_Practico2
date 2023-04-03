package com.example.desafio_practico.salario

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.desafio_practico.R

class AdaptadorSalario(private val context: Activity, var salario: List<Salario>) :
    ArrayAdapter<Salario?>(context, R.layout.activity_ejercicio2 , salario) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        rowview = view ?: layoutInflater.inflate(R.layout.salario_layout, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvSalario = rowview!!.findViewById<TextView>(R.id.tvSalario)

        tvNombre.text = "nombre : " + salario[position].nombre
        tvSalario.text = "salario : " + salario[position].salario
        return rowview


    }

}