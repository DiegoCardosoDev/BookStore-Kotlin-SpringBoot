package br.com.diego.salebooks.exeptions

class handleArgumentNotValidException(
        override val message: String,
        val erroCode: String
) : Exception()
