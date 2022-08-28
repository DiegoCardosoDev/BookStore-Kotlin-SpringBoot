package br.com.diego.salebooks.controller

import br.com.diego.salebooks.mapper.PurchaseMapper
import br.com.diego.salebooks.service.PurchaseService
import com.mercadolivro.controller.request.PostPurchaseRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("purchase")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){

        purchaseService.create(purchaseMapper.toModel(request))
    }

}