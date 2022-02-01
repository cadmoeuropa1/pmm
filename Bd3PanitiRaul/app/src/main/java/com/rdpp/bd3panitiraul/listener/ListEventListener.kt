package com.rdpp.bd3panitiraul.listener

import com.rdpp.bd3panitiraul.dataclass.ShoppingList

interface ListEventListener {
    fun deleteList(shoppingList: ShoppingList)
    fun editList(shoppingList: ShoppingList)
}