package br.com.diego.salebooks.exeptions

class NotFoundExeption(
        override val message:String,
        val erroCode: String
) : Exception()