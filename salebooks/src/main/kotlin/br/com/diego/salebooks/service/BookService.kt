package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.BookStatus
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }


    fun findAllBooks(page: Pageable): Page<BookModel> {
        return bookRepository.findAll(page)
    }

    fun findActive(page: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, page)

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
