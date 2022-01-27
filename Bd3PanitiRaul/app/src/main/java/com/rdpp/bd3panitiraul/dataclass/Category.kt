package com.rdpp.bd3panitiraul.dataclass

data class Category(val cat_Id: Int, val name: String) {
    constructor(name: String) : this(0, name)

    override fun toString(): String {
        return name
    }
}
