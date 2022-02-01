package com.rdpp.bd3panitiraul.listener

import com.rdpp.bd3panitiraul.dataclass.Product
import com.rdpp.bd3panitiraul.dataclass.ShoppingList

interface ProductEventListener {
    fun deleteProduct(product: Product)
    fun addToList(product: Product)
}
