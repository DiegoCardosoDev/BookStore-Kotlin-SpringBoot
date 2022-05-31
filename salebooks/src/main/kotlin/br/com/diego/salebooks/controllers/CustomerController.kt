package br.com.diego.salebooks.controllers

import br.com.diego.salebooks.controllers.request.CustomerPutRequest
import br.com.diego.salebooks.controllers.request.PostCustomerRequest
import br.com.diego.salebooks.controllers.response.CustomerResponse
import br.com.diego.salebooks.extension.toCustomerModel
import br.com.diego.salebooks.extension.toResponse
import br.com.diego.salebooks.service.CustomerService
import lombok.extern.slf4j.Slf4j
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {


    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10)pageable: Pageable,
               @RequestParam name: String?): Page<CustomerResponse> {

        return customerService.getAll(pageable).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: CustomerPutRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }


}