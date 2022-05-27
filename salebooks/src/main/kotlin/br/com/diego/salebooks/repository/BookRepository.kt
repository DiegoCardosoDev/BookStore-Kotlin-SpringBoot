package br.com.diego.salebooks.repository

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.PathVariable

interface BookRepository : CrudRepository<BookModel, Int> {

    fun findByStatus(status: BookStatus): List<BookModel>
    fun findByTitleContainingIgnoreCase(title: String): List<BookModel>

}