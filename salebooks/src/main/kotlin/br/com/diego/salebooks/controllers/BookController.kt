package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.PostBookRequest
import br.com.diego.salebooks.extension.toBookModel
import br.com.diego.salebooks.models.BookModel
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.CustomerService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Slf4j
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


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }


}