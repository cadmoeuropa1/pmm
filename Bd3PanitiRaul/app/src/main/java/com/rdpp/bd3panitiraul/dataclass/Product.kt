package com.rdpp.bd3panitiraul.dataclass

data class Product(val prod_Id: Int, val name: String, val cat_Id: Int, val image: String) {

    constructor(name: String, cat_Id: Int, image: String) : this(0, name, cat_Id, image)
}
