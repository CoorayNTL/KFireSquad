package com.project.k_firesquad.models

data class BuyerProduct(
    var buyerProductID: String? = null,
    var productName : String? = null,
    var productQty : String? = null,
    var productRate : String? = null,
    var description: String? = null,
    var sellerName : String? = null,
    var sellerLocation: String? = null,
    var offerStartDate : String? = null,
    var offerEndDate: String? = null,
    var buyerID: String? = null,
)