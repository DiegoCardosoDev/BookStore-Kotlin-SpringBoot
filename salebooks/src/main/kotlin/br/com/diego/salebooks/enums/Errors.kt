package br.com.diego.salebooks.enums

enum class Errors(val code: String, val message: String) {
    BK000("BK-000", "unauthorized"),
    BK101("BK-101", "[%s] não existe"),
    BK102("BK-102", "não é possivel deletra um livro [%s] "),
    BK201("BK-201", "[%s]"),
    BK001("BK-001", "request inválido[%s]")

}