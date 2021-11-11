package com.mse.dam2_raulpaniti_ejercicio2_bloque1

import android.content.Intent

interface EventListener {
    fun onClickListener(monument: Monument, position: Int) {

    }

    fun onLongClickListener(monument: Monument, position: Int) {

    }

    fun call(monument: Monument, position: Int) {

    }
    fun sendMail(monument: Monument, position: Int)
}