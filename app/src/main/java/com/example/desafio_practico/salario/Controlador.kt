package com.example.desafio_practico.salario

class Controlador(
    private val view: Iview
) : Icontrolador {
    override fun saldoNeto(saldo: Double?, nombre: String?) {

        val deducion = (saldo!! * 0.03) + (saldo!! * 0.04) + (saldo!! * 0.05)
        val salarioNeto = saldo - deducion

        view.resultado("Salario Neto $$salarioNeto")


    }


}