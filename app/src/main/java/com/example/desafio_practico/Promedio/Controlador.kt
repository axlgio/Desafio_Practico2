package com.example.desafio_practico.Promedio


class Controlador (private val view: Iview
):Icontrolador {
    override fun promedio(n1: Double?, n2: Double?, n3: Double?, n4: Double?, n5: Double?, studiante: String?) {
        val promedio = (n1!!+n2!!+n3!!+n4!!+n5!!)/5
        var resultado : String = ""

        if (promedio >= 6.0) resultado = "sí, Aprobo"
        if (promedio < 6.0) resultado = "Reprobo"

        view.resultado("$studiante, $resultado con $promedio")
    }


}