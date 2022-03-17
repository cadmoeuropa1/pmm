package com.mjpg.tiendasfragment

interface EventosListener {
    fun editar(id:Long)
    fun onFavorito(tienda:Tienda)
    fun borrarTienda(id:Long)

}
