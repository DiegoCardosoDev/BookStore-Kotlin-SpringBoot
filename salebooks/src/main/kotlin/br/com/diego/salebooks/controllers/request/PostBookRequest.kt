package br.com.diego.salebooks.controllers.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostBookRequest(

        @field:NotEmpty(message = "nome não pode ser vazio")
        var name: String,

        @field:NotNull(message = "preço deve informado")
        var price: BigDecimal,

        @JsonAlias("customer_id")
        var customerId: Int

)

data class PutBookRequest(

        var name: String?,
        
        var price: BigDecimal?

) {

}
