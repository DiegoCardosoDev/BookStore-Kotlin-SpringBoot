package br.com.diego.salebooks.exeptions

import br.com.diego.salebooks.controllers.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handExeption(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error= ErrorResponse(
                400, "esse recurso não existe",
                "00001",
                null

        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)

    }
}