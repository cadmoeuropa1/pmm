package com.rdpp.proyecto

open class Persona(nombre: String, apellidos: String) {
    var nombre: String = ""
        get() = "Nombre: $field"                               //El field coge la propiedad inmediatamente superior a la linea, en este caso el nombre.
        set(value) {
            field = if (value.isEmpty()) "Sin nombre" else value
        }
    var apellidos: String =
        ""                                                 // Los get devuelven SIEMPRE lo que hayas puesto. SIEMPRE SIEMPRE.
        /*get() {
            return "Apellidos: $field"                        //Otra forma de hacer un get.
        }*/
        /*set(value) {
            field = value.uppercase()
        }*/
    var edad: Int

    init {
        this.nombre = nombre
        this.apellidos = apellidos
        edad = 0
    }

    constructor(nombre: String, apellidos: String, edad: Int)
            : this(nombre, apellidos) {
        this.edad = edad
        this.nombre = nombre
        this.apellidos = apellidos
    }
    open fun visualizar(){
        println("$nombre $apellidos, $edad")
    }
}