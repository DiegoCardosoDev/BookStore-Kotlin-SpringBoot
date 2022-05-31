package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    @GetMapping
    fun findAllBooks(title: String?): List<BookModel> {

        title?.let {
            return bookRepository.findByTitleContainingIgnoreCase(title)
        }
        return bookRepository.findAll().toList()
    }

    fun findActive(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO).toList()

    }

    fun findById(@PathVariable id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun deleteByCustumer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun activationBook(id: Int) {
        val book = findById(id)
        book.status = BookStatus.ATIVO
        update(book)

    }


}
