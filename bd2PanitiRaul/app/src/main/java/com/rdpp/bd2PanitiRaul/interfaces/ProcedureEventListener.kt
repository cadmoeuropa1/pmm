package com.rdpp.bd2PanitiRaul.interfaces

import com.rdpp.bd2PanitiRaul.dataclasses.Procedure

interface ProcedureEventListener {
    fun changeStatus(procedure: Procedure)
}