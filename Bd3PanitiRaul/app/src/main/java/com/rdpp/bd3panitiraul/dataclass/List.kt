package com.rdpp.bd3panitiraul.dataclass

data class List(val list_Id: Int, val name: String) {
    constructor(name: String) : this(0, name)
}
