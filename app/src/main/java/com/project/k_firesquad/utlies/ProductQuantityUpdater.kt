package com.project.k_firesquad.utlies
interface ProductQuantityUpdateListener {
    fun onQuantityUpdated(updatedQty: Int)
    fun onInvalidQuantity()
}

class ProductQuantityUpdater(private val listener: ProductQuantityUpdateListener) {
    fun updateQuantity(productQty: Int, insertedQty: Int ) : Int {
        var updatedQty = 0
        if(insertedQty >= productQty ){
            listener.onInvalidQuantity()
            return 0
        } else {
            updatedQty =   productQty - insertedQty
            listener.onQuantityUpdated(updatedQty)
        }
        return updatedQty
    }
}
