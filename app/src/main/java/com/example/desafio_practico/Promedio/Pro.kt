package com.example.desafio_practico.Promedio

class Pro {
    fun key(key: String?) {
    }

    var studiante: String? = null
    var n1: Double? = 0.0
    var n2: Double? = 0.0
    var n3: Double? = 0.0
    var n4: Double? = 0.0
    var n5: Double? = 0.0
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()

    constructor() {}
    constructor(studiante: String?, n1 : Double?, n2 : Double?, n3 : Double?, n4 : Double?, n5 : Double?) {
        this.studiante = studiante
        this.n1 = n1
        this.n2 = n2
        this.n3 = n3
        this.n4 = n4
        this.n5 = n5
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "studiante" to studiante,
            "n1" to n1,
            "n2" to n2,
            "n3" to n3,
            "n4" to n4,
            "n5" to n5,
            "key" to key,
            "per" to per
        )
    }
}