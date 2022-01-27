package com.rdpp.bd3panitiraul.dataclass

data class Product_List(val code: Int, val list_Id: Int, val prod_Id: Int, val quantity: Int){

    constructor(list_Id: Int, prod_Id: Int, quantity: Int): this (0, list_Id, prod_Id, quantity)
}
