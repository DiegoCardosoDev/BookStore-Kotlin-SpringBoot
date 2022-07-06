package br.com.diego.salebooks.models

import br.com.diego.salebooks.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(


        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var email: String,

        @Column
    @Enumerated(EnumType.STRING)
        var status: CustomerStatus


)