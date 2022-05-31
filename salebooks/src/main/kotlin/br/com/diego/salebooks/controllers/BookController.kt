package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.PostBookRequest
import br.com.diego.salebooks.controllers.request.PutBookRequest
import br.com.diego.salebooks.controllers.response.BookResponse
import br.com.diego.salebooks.extension.toBookModel
import br.com.diego.salebooks.extension.toResponse
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.CustomerService
import lombok.extern.log4j.Log4j
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))

    }

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAllBooks(pageable).map { it.toResponse() }
    }

    @GetMapping("/actives")
    fun getActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findActive(pageable).map { it.toResponse() }

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
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

    @PutMapping("activation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun bookActivation(@PathVariable id: Int) {
        bookService.activationBook(id)
    }


}