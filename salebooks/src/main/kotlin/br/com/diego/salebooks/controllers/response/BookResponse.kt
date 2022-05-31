package br.com.diego.salebooks.controllers.response

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.CustomerModel
import java.math.BigDecimal

data class BookResponse(

    var id: Int? = null,

    var title: String,

    var price: BigDecimal,

    var status: BookStatus? = null,

    var customer: CustomerModel? = null
)
