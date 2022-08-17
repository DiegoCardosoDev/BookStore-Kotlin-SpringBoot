package br.com.diego.salebooks.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PutCustomerRequest(

        @field:NotEmpty(message = "nome não pode ser vazio")
        var name: String,

        @field:Email(message = "email deve ser válido")
        var email: String
)