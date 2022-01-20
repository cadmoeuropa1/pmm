package com.rdpp.ej2basesdedatos.interfaces

import com.rdpp.ej2basesdedatos.dataclasses.Case

interface EventListener {
    fun showDetails(case: Case)
}