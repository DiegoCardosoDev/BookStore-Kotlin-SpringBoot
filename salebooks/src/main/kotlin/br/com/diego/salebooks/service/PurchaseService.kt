package br.com.diego.salebooks.service

import br.com.diego.salebooks.event.PurchaseEvent
import br.com.diego.salebooks.models.PurchaseModel
import br.com.diego.salebooks.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purcahseRepository: PurchaseRepository,
        private  val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel){
        purcahseRepository.save(purchaseModel)
        applicationEventPublisher.publishEvent(PurchaseEvent(this,purchaseModel))
    }

}
