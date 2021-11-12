package com.rdpp.proyecto

class Alumno(nombre:String, apellidos:String, curso:String)
    :Persona(nombre,apellidos) {
    var curso:String
    init {
        this.curso=curso
    }
   override fun visualizar(){
       super.visualizar()
       println("El curso es $curso")
   }
}