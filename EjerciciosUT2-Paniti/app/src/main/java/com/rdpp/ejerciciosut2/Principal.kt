package com.rdpp.ejerciciosut2

fun main() {
    var coche1=Vehiculo_1()
    var coche2=Vehiculo_1()
    coche2.color="Rojo"
    coche2.marca="Volvo"
    var coche3=Vehiculo_1()
    coche3.color="Azul"
    coche3.modelo="Astra"
    val listaCoches= mutableListOf<Vehiculo_1>(coche1,coche2,coche3)
    for(i in 0..listaCoches.size-1){
        listaCoches[i].visualizar()
    }
}