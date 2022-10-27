package com.salebook.controller

import com.salebook.controller.request.PostCustomerRequest
import com.salebook.controller.request.PutCustomerRequest
import com.salebook.controller.response.CustomerResponse
import com.salebook.extension.toCustomerModel
import com.salebook.extension.toResponse
import com.salebook.security.UserCanOnlyAccessTheirOwnResource
import com.salebook.service.CustomerService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    private val customerService : CustomerService
) {

    @ApiOperation(value = " Carrega customer pelo nome ou lista todos customers")
    @ApiResponses(value = [ApiResponse(code = 200, message = "customer carregado com sucesso!")])
    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @ApiOperation(value = " Cadastra e salva um customer")
    @ApiResponses(value = [ApiResponse(code = 201, message = "customer criado com sucesso")])
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @ApiOperation(value = " Carrega customer pelo ID")
    @ApiResponses(value = [ApiResponse(code = 200, message = "customer carregado")])
    @UserCanOnlyAccessTheirOwnResource
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @ApiOperation(value = " Atualiza customer pelo ID")
    @ApiResponses(value = [ApiResponse(code = 204, message = "customer atualizado")])
    @UserCanOnlyAccessTheirOwnResource
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

}