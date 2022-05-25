package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    @GetMapping
    fun getAll(title: String?): List<BookModel> {

        title?.let {
            return bookRepository.findByTitleContainingIgnoreCase(title)
        }
        return bookRepository.findAll().toList()
    }

    fun findActive(): List<BookModel>{
        return bookRepository.findByStatus(BookStatus.ATIVO).toList()

    }


}
