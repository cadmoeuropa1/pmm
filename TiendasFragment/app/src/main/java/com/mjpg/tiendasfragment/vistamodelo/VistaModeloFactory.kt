package com.mjpg.tiendasfragment.vistamodelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class VistaModeloFactory(private val id:Long):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VistaModelo(id) as T
    }
}