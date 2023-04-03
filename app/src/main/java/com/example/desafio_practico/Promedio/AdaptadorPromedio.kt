package com.example.desafio_practico.Promedio

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.desafio_practico.R

class AdaptadorPromedio(private val context: Activity, var promedio: List<Pro>) :
        ArrayAdapter<Pro?>(context, R.layout.activity_ejercicio1 , promedio) {
        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            // Método invocado tantas veces como elementos tenga la coleccion personas
            // para formar a cada item que se visualizara en la lista personalizada
            val layoutInflater = context.layoutInflater
            var rowview: View? = null
            // optimizando las diversas llamadas que se realizan a este método
            // pues a partir de la segunda llamada el objeto view ya viene formado
            // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
            // desgaste de bateria del dispositivo
            rowview = view ?: layoutInflater.inflate(R.layout.promedio_layout, null)
            val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
            val tvNota1 = rowview!!.findViewById<TextView>(R.id.tvNota1)
            val tvNota2 = rowview!!.findViewById<TextView>(R.id.tvNota2)
            val tvNota3 = rowview!!.findViewById<TextView>(R.id.tvNota3)
            val tvNota4 = rowview!!.findViewById<TextView>(R.id.tvNota4)

            tvNombre.text = "Nombre : " + promedio[position].studiante
            tvNota1.text = "Nota1 : " + promedio[position].n1
            tvNota2.text = "Nota2 : " + promedio[position].n2
            tvNota3.text = "Nota3 : " + promedio[position].n3
            tvNota4.text = "Nota4 : " + promedio[position].n4
            return rowview
        }

}