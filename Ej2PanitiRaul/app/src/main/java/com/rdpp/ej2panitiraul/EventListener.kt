package com.rdpp.ej2panitiraul

interface EventListener {
    fun onClickListener(monument: Monument, position:Int){

    }
    fun onLongClickListener(monument: Monument, position:Int){

    }
    fun call(monument: Monument, position:Int){

    }
    fun sendMail(monument: Monument, position:Int){

    }
}