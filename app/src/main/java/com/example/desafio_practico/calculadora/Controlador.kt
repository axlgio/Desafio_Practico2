package com.example.desafio_practico.calculadora

class Controlador(
    private val view: Iview
) : Icontrolador {


    override fun calculadora(a: Double?, b: Double?, aOperar: String?) {

        var resultado: String = ""
        var operar: Double = 0.0
        if (a != null && b != null && aOperar != null)
            when (aOperar) {
                "Dividir" -> operar = a / b
                "Restar" -> operar = a - b
                "Sumar" -> operar = a + b
                "Multiplicar" -> operar = a * b

            }
        resultado = operar.toString()
        view.resultado("Resultado= " + resultado)
    }


}