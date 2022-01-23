package com.rdpp.bd2PanitiRaul.interfaces

import com.rdpp.bd2PanitiRaul.dataclasses.Case

interface CaseEventListener {
    fun showDetails(case: Case)

}