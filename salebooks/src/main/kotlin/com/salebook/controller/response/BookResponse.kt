package com.salebook.controller.response

import com.salebook.enums.BookStatus
import com.salebook.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: BookStatus? = null
)