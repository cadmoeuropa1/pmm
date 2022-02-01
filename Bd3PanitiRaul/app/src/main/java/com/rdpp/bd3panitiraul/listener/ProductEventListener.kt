package com.rdpp.bd3panitiraul.listener

import com.rdpp.bd3panitiraul.dataclass.Product

interface ProductEventListener {
    fun deleteProduct(product: Product)

}
