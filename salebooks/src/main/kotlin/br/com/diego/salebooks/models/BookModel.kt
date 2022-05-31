package br.com.diego.salebooks.models

import br.com.diego.salebooks.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "books")
data class BookModel(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var title: String,

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
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw Exception("NÃO É POSSIVEL ALTERAR UM LIVRO CANCELADO OU DELETADO!!!   ")
            }
            field = value

        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }

}
