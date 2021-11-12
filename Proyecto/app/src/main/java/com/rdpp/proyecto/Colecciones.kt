package com.rdpp.proyecto

fun main(){
    // Colección de solo lectura
    val listaFrutas= listOf("manzana", "pera","platano")
    for (fruta in listaFrutas){
        println(fruta)
    }
    for (i in 0 until listaFrutas.size){
        println(listaFrutas[i])
        println(listaFrutas.get(i))
    }
    //Colección de lectura y escritura
    val persona1 = Persona("Marta","Pardo")
    val persona2 = Persona("Pepe", "2")
    var lista2= mutableListOf<Persona>(persona1,persona2)
    for (i in 0..lista2.size-1){
        lista2[i].visualizar()
    }
}