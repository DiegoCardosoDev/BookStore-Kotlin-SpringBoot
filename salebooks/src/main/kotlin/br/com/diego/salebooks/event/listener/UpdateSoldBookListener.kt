package br.com.diego.salebooks.event.listener

import br.com.diego.salebooks.event.PurchaseEvent
import br.com.diego.salebooks.service.BookService
import br.com.diego.salebooks.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookListener(
        private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {

        bookService.purchase(purchaseEvent.purchaseModel.books)

    }

}
