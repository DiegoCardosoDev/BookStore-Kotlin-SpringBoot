package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.PostBookRequest
import br.com.diego.salebooks.controllers.request.PutBookRequest
import br.com.diego.salebooks.extension.toBookModel
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.CustomerService
import lombok.extern.log4j.Log4j
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Slf4j
@Log4j
@RestController
@RequestMapping("books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))

    }

    @GetMapping
    fun getAll(@RequestParam title: String?): List<BookModel> {
        return bookService.findAllBooks(title)
    }

    @GetMapping("/actives")
    fun getActives(): List<BookModel> {
        return bookService.findActive()

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookModel {
        return bookService.findById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }


}