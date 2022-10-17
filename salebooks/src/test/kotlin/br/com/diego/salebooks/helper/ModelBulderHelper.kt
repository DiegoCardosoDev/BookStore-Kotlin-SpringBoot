package br.com.diego.salebooks.helper

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.enums.Role
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.models.PurchaseModel
import java.math.BigDecimal
import java.util.*

 fun buildCustomer(
        id: Int? = null,
        name: String = "customerName",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "pass"
) = CustomerModel(
        id = id,
        name = name,
        email = email,
        status = CustomerStatus.ATIVO,
        password = password,
        roles = setOf(Role.CUSTOMER)
)

 fun buildPurchase(
        id: Int?=null,
        customer: CustomerModel= buildCustomer(),
        books: MutableList<BookModel> = mutableListOf(),
        nfe:String? =UUID.randomUUID().toString(),
        price: BigDecimal = BigDecimal.TEN

)= PurchaseModel(
        id = id,
        customer =customer,
        books = books,
        nfe=nfe,
        price = price,

        )