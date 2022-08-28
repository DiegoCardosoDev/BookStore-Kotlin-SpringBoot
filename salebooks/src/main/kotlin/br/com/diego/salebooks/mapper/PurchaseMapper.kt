package br.com.diego.salebooks.mapper

import br.com.diego.salebooks.models.PurchaseModel
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.CustomerService
import br.com.diego.salebooks.controller.request.PostPurchaseRequest
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
        private val bookService: BookService,
        private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)
        return PurchaseModel(
                customer = customer,
                books = books.toMutableList(),
                price = books.sumOf { it.price }
        )
    }
}