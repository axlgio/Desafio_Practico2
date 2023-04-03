package com.example.desafio_practico.salario

class Salario {

        fun key(key: String?) {
        }

        var nombre: String? = null
        var salario: Double? = 0.0
        var key: String? = null
        var per: MutableMap<String, Boolean> = HashMap()

        constructor() {}
        constructor(nombre: String?, salario : Double?) {
            this.nombre = nombre
            this.salario = salario
        }

        fun toMap(): Map<String, Any?> {
            return mapOf(
                "nombre" to nombre,
                "salario" to salario,
                "key" to key,
                "per" to per
            )
        }
    }
