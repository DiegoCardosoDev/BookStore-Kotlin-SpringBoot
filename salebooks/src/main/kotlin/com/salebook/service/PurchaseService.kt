package com.salebook.service

import com.salebook.events.PurchaseEvent
import com.salebook.model.PurchaseModel
import com.salebook.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        println("Disparando evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Finalização do processamento!")
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }

}
