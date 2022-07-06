package br.com.diego.salebooks.controllers.request

data class PostCustomerRequest(

        var name: String,
        var email: String
)

data class CustomerPutRequest(

        var name: String,
        var email: String

)