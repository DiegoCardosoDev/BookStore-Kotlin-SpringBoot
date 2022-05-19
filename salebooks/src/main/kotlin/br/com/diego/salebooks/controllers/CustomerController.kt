package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.PostCustomerRequest
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("custumer")
class CustomerController(val repository: CustomerRepository) {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCustomer(): MutableIterable<CustomerModel> {
        return repository.findAll()

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customerModel: PostCustomerRequest) {
        println(customerModel)

    }


}