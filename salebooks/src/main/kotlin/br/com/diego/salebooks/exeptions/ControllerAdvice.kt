package br.com.diego.salebooks.exeptions

import br.com.diego.salebooks.controller.response.ErrorResponse
import br.com.diego.salebooks.controller.response.FieldErrorResponse
import br.com.diego.salebooks.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(BadRequestExeption::class)
    fun handleBadRequest(ex: BadRequestExeption, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.message,
                ex.erroCode,
                null

        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.message,
                Errors.ML001.code,
                ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }

        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)

    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handAccessDeniedException(ex: NotFoundExeption, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                Errors.ML000.message,
                Errors.ML000.code,
                null

        )
        return ResponseEntity(error, HttpStatus.FORBIDDEN)

    }

}




