package br.com.diego.salebooks.repository

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {

    fun findByStatus(status: BookStatus, page: Pageable): Page<BookModel>
    fun findByTitleContainingIgnoreCase(title: String): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

    fun findAll(page: Pageable): Page<BookModel>

}