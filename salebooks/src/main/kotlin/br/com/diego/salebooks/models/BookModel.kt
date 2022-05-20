package br.com.diego.salebooks.models

import br.com.diego.salebooks.enums.Status
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

    @Column
    @Enumerated(EnumType.STRING)
    var status: Status? = null,

    @ManyToOne
    @JoinColumn(name = "custumer_id")
    var customerModel: CustomerModel? = null




)