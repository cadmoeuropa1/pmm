package com.mjpg.tiendasfragment.adaptadores

import com.mjpg.tiendasfragment.modelo.Tienda

interface EventosListener {
    fun editar(id:Long)
    fun onFavorito(tienda: Tienda)
    fun borrarTienda(id:Long)

}
