package br.com.diego.salebooks.service

import br.com.diego.salebooks.models.PurchaseModel
import br.com.diego.salebooks.repository.PurchaseRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val purcahseRepository: PurchaseRepository) {

    fun create(purchaseModel: PurchaseModel){
        purcahseRepository.save(purchaseModel)
    }

}
