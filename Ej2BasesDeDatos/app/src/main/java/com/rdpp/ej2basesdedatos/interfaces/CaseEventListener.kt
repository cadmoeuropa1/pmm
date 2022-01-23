package com.rdpp.ej2basesdedatos.interfaces

import com.rdpp.ej2basesdedatos.dataclasses.Case
import com.rdpp.ej2basesdedatos.dataclasses.Procedure

interface CaseEventListener {
    fun showDetails(case: Case)

}