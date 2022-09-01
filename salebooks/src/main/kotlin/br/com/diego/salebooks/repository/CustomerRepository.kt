package br.com.diego.salebooks.repository

import br.com.diego.salebooks.models.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int>{

    fun findByNameContainingIgnoreCase(name: String): List<CustomerModel>
    fun findAll(pageable: Pageable):Page<CustomerModel>
    fun existsByEmail(email: String):Boolean
    fun findByEmail(email: String):CustomerModel?
}