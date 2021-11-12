package com.rdpp.ejerciciosut2

class Vehiculo_2(marca: String, modelo: String, color: String) {
    var marca: String = ""
    var modelo: String = ""
    var color: String = ""
    init {
        this.marca = marca
        this.modelo= modelo
        this.color = color
    }
        getMarca(){
            return "Marca: $marca"
        }
    fun visualizar() {
        println("$marca $modelo $color")
    }
}