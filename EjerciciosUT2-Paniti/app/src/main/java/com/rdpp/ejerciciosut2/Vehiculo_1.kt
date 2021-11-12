package com.rdpp.ejerciciosut2

class Vehiculo_1 (){
    var marca: String = ""
        get() = "Marca: $field"
        set(value) {
            field = if (value.isEmpty()) "Empty" else value
        }
    var modelo: String = ""
        get() = "Modelo: $field"
        set(value) {
            field = if (value.isEmpty()) "Empty" else value
        }
    var color: String = ""
        get() = "Color: $field"
        set(value) {
            field = if (value.isEmpty()) "Empty" else value
        }

    fun visualizar() {
        println("$marca $modelo $color")
    }
}