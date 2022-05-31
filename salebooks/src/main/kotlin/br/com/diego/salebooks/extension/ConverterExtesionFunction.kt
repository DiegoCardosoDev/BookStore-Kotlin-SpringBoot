package br.com.diego.salebooks.extension

import br.com.diego.salebooks.controllers.request.CustomerPutRequest
import br.com.diego.salebooks.controllers.request.PostBookRequest
import br.com.diego.salebooks.controllers.request.PostCustomerRequest
import br.com.diego.salebooks.controllers.request.PutBookRequest
import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.models.status.customerStatus

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(
        name = this.name,
        email = this.email,
        status = customerStatus.ATIVO
    )
}

fun CustomerPutRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(

        id=previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status
    )
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        title = this.title,
        price = this.price,
        customer = customer,
        status = BookStatus.ATIVO
    )
}

fun PutBookRequest.toBookModel(book: BookModel): BookModel {
    return BookModel(
        id= book.id,
        title=this.title?: book.title,
        price=this.price?: book.price,
        status = book.status,
        customer = book.customer
    )
}
