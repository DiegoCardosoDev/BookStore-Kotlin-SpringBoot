package br.com.diego.salebooks.controllers.response

import br.com.diego.salebooks.enums.CustomerStatus

data class CustomerResponse(

        var id: Int? = null,

        var name: String,

        var email: String,

        var status: CustomerStatus
)
