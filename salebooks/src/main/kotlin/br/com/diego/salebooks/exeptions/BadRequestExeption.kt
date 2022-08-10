package br.com.diego.salebooks.exeptions

class BadRequestExeption(
        override val message:String,
        val erroCode: String
) : Exception()