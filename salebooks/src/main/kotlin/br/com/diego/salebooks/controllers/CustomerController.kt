package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.PostCustomerRequest
import br.com.diego.salebooks.models.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("custumer")
class CustomerController {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCustomer(): CustomerModel {
        return CustomerModel(1, "Diego", "diegocardoso@gmail.com")

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customerModel: PostCustomerRequest) {
        println(customerModel)

    }


}