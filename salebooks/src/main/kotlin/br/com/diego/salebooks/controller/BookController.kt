package br.com.diego.salebooks.controller

import br.com.diego.salebooks.controller.request.PostBookRequest
import br.com.diego.salebooks.controller.request.PutBookRequest
import br.com.diego.salebooks.controller.response.BookResponse
import br.com.diego.salebooks.extension.toBookModel
import br.com.diego.salebooks.extension.toResponse
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.CustomerService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import lombok.extern.log4j.Log4j
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(
        val bookService: BookService,
        val customerService: CustomerService
) {

    @ApiOperation(value = " Cadastra e salva um livro")
    @ApiResponses(value = [ApiResponse(code = 200, message = "livro criado com sucesso")])
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @ApiOperation(value = " Carrega todos livros cadastrados ")
    @ApiResponses(value = [ApiResponse(code = 200, message = "livros carregados")])
    @GetMapping("all-books")
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {

        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @ApiOperation(value = " Carrega todos livros ativos ")
    @ApiResponses(value = [ApiResponse(code = 200, message = "livros ativos carregados")])
    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
            bookService.findActives(pageable).map { it.toResponse() }

    @ApiOperation(value = " Carrega livro pelo ID")
    @ApiResponses(value = [ApiResponse(code = 200, message = "livros carregado")])
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @ApiOperation(value = " Deleta livro pelo ID")
    @ApiResponses(value = [ApiResponse(code = 204, message = "livro deletado com sucesso")])
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @ApiOperation(value = " Atualiza livro pelo ID")
    @ApiResponses(value = [ApiResponse(code = 204, message = "livros atualizado")])
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

}