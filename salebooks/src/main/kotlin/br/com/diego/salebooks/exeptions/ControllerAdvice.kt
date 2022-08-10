package br.com.diego.salebooks.exeptions

import br.com.diego.salebooks.controllers.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundExeption::class)
    fun handExeption(ex: NotFoundExeption, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.message,
                ex.erroCode,
                null

        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)

    }
}