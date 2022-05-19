package br.com.diego.salebooks.extension

import br.com.diego.salebooks.controllers.request.PostCustomerRequest
import br.com.diego.salebooks.controllers.request.PutCustomerRequest
import br.com.diego.salebooks.models.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}
