package br.com.diego.salebooks.event.listener

import br.com.diego.salebooks.event.PurchaseEvent
import br.com.diego.salebooks.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
        private val purchaseService: PurchaseService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe=nfe)
        purchaseService.update(purchaseModel)

    }

}
