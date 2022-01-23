package com.rdpp.ej2basesdedatos.interfaces

import com.rdpp.ej2basesdedatos.dataclasses.Procedure

interface ProcedureEventListener {
    fun changeStatus(procedure: Procedure)
}