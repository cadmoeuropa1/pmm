package com.rdpp.bd3panitiraul.listener

import com.rdpp.bd3panitiraul.dataclass.Product

interface EventListener {
    fun deleteProduct(product: Product)

}
