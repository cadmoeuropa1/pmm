package com.rdpp.proyecto

fun main() {
    var objeto:String?=null
    //println(objeto.toString())
    println(objeto!!.toString())
    /*
    var numero1=1
    var numero2=5
    println("numero" + " " + numero1)
    println("numero $numero2")
    println("Suma")
    println("$numero1 + $numero2 = ${sumar(numero1,numero2)}")
    //Se pueden hacer varias cosas con la misma funcion, 10 de 10
    nuevoConcepto("SOBRECARGA")
    mostrarProducto("Lapicero")
    mostrarProducto("Boligrafo", "15%")
    mostrarProducto("Sacapuntas", "10%", "2 dias")
    mostrarProducto("Archivador", vigencia=" hasta 20 Abril")
*/

}

fun nuevoConcepto(topico: String) {
    println()
    println(topico)
}

fun sumar(a:Int, b:Int):Int{
    return a+b
}
fun mostrarProducto(nombre:String, promo:String="Sin promocion",
                    vigencia:String="Hasta agotar existencias"){
    println("Nombre = $nombre $promo $vigencia")
}
