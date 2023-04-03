package com.example.desafio_practico.Promedio


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
import com.example.desafio_practico.R
import com.example.desafio_practico.calculadora.Ejercicio3
import com.example.desafio_practico.salario.SalarioActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class PromedioActivity : AppCompatActivity() {

    var consultaOrdenada: Query = refPromedio.orderByChild("studiante")
    var promedio: MutableList<Pro>? = null
    var listaPromedio: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)
        inicializar()
    }


    private fun inicializar() {
        val fab_agregar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab_agregar)
        listaPromedio = findViewById<ListView>(R.id.ListaPromedio)

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaPromedio!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), AddPromedioActivity::class.java)
                intent.putExtra("accion", "e") // Editar
                intent.putExtra("key", promedio!![i].key)
                intent.putExtra("studiante", promedio!![i].studiante)
                intent.putExtra("n1", promedio!![i].n1)
                intent.putExtra("n2", promedio!![i].n2)
                intent.putExtra("n3", promedio!![i].n3)
                intent.putExtra("n4", promedio!![i].n4)
                intent.putExtra("n5", promedio!![i].n5)
                startActivity(intent)
            }
        })

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaPromedio!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ): Boolean {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                val ad = AlertDialog.Builder(this@PromedioActivity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"
                ) { dialog, id ->
                    promedio!![position].studiante?.let {
                        refPromedio.child(it).removeValue()
                    }
                    Toast.makeText(
                        this@PromedioActivity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        Toast.makeText(
                            this@PromedioActivity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }

        fab_agregar.setOnClickListener(View.OnClickListener { // Cuando el usuario quiere agregar un nuevo registro
            val i = Intent(getBaseContext(), AddPromedioActivity::class.java)
            i.putExtra("accion", "a") // Agregar
            i.putExtra("key", "")
            i.putExtra("studiante", "")
            i.putExtra("n1", "")
            i.putExtra("n2", "")
            i.putExtra("n3", "")
            i.putExtra("n4", "")
            i.putExtra("n5", "")
            startActivity(i)
        })

        promedio = ArrayList<Pro>()

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                promedio!!.removeAll(promedio!!)
                println(dataSnapshot)

                for (dato in dataSnapshot.getChildren()) {
                    println(dato)
                    val pro : Pro? = dato.getValue(Pro::class.java)
                    pro?.key(dato.key)
                    if (pro != null) {
                        promedio!!.add(pro)
                    }
                }
                val adapter = AdaptadorPromedio(
                    this@PromedioActivity,
                    promedio as ArrayList<Pro>
                )
                listaPromedio!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refPromedio: DatabaseReference = database.getReference("promedio")
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