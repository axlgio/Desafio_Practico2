package com.example.desafio_practico.salario

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.desafio_practico.Promedio.PromedioActivity
import com.example.desafio_practico.R
import com.example.desafio_practico.calculadora.Ejercicio3
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class SalarioActivity : AppCompatActivity() {


    var consultaOrdenada: Query = refSalario.orderByChild("nombre")
    var salario: MutableList<Salario>? = null
    var listaSalario: ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        inicializar()
    }

    private fun inicializar() {
        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_ag)
        listaSalario = findViewById<ListView>(R.id.ListaSalario)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaSalario!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), AddSalarioActivity::class.java)
                intent.putExtra("accion", "e") // Editar
                intent.putExtra("key", salario!![i].key)
                intent.putExtra("nombre", salario!![i].nombre)
                intent.putExtra("salario", salario!![i].salario)
                startActivity(intent)
            }
        })

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaSalario!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ): Boolean {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(this@SalarioActivity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { dialog, id ->
                    salario!![position].nombre?.let {
                        refSalario.child(it).removeValue()
                    }
                    Toast.makeText(
                        this@SalarioActivity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            this@SalarioActivity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }

        fab_agregar.setOnClickListener(View.OnClickListener { // Cuando el usuario quiere agregar un nuevo registro
            val i = Intent(getBaseContext(), AddSalarioActivity::class.java)
            i.putExtra("accion", "a") // Agregar
            i.putExtra("key", "")
            i.putExtra("nombre", "")
            i.putExtra("salario", "")
            startActivity(i)
        })


        salario = ArrayList<Salario>()

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                salario!!.removeAll(salario!!)
                println(dataSnapshot)

                for (dato in dataSnapshot.getChildren()) {
                    println(dato)
                    val sal : Salario? = dato.getValue(Salario::class.java)
                    sal?.key(dato.key)
                    if (sal != null) {
                        salario!!.add(sal)
                    }
                }
                val adapter = AdaptadorSalario(
                    this@SalarioActivity,
                    salario as ArrayList<Salario>
                )
                listaSalario!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refSalario: DatabaseReference = database.getReference("salario")
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