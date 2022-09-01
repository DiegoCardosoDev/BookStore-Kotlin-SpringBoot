package br.com.diego.salebooks.controller.request

import br.com.diego.salebooks.validation.EmailAvalable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(

        @field:NotEmpty(message = "nome não pode ser vazio")
        var name: String,

        @field:Email(message = "email deve ser válido")
        @EmailAvalable
        var email: String,
        @field:NotEmpty
        var password: String
)

data class CustomerPutRequest(

        var name: String,
        @EmailAvalable
        var email: String

)