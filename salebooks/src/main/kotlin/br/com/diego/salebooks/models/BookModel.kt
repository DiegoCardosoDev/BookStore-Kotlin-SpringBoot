package br.com.diego.salebooks.models

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.enums.Errors
import br.com.diego.salebooks.exeptions.BadRequestExeption
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "books")
data class BookModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var price: BigDecimal,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null

) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO)
                throw BadRequestExeption(Errors.BK102.message.format(field), Errors.BK102.code)

            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatus?): this(id, name, price, customer) {
        this.status = status
    }

}