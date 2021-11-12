package com.rdpp.proyecto

fun main() {
    //mostrarNombres("Ana", "Pepe", "Juan")
    //mostrarNombres2("Ana","Pepe","Juan","Julian","Alfonso")
    ejemplowhen()
}

fun ejemplowhen() {
    var numero = 25
    when(numero){
        10-> println("vale diez")
        7,5,9 -> println("son impares")
        in 20..30 -> println("vale entre 20 y 30")
        !in 30..40 -> println("no está entre 30 y 40")
        is Int -> println("Es un numero")
        else -> println("No es nada")
    }
}


fun mostrarNombres(nombre1: String, nombre2: String, nombre3: String) {
    println(nombre1)
    println(nombre2)
    println(nombre3)
}

fun mostrarNombres2(vararg personas: String) {
    println()
    for (persona in personas)
        print(persona+"\t")
    for (posicion in 0..personas.size-1)
        print(personas[posicion]+"\t")
    for (posicion2 in 0 until personas.size-1)
        print(personas[posicion2]+"\t")

}