package br.com.diego.salebooks.controller

import br.com.diego.salebooks.controller.request.PostCustomerRequest
import br.com.diego.salebooks.controller.request.PutCustomerRequest
import br.com.diego.salebooks.controller.response.CustomerResponse
import br.com.diego.salebooks.extension.toCustomerModel
import br.com.diego.salebooks.extension.toResponse
import br.com.diego.salebooks.security.UserCanOnlyAccessTheirOwnResource
import br.com.diego.salebooks.service.CustomerService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Slf4j
@RestController
@RequestMapping("customer")
class CustomerController(
        val customerService : CustomerService
) {

    @ApiOperation(value = " Carrega todos customers")
    @ApiResponses(value = [ApiResponse(code = 200, message = "customer carregado com sucesso!")])
    @UserCanOnlyAccessTheirOwnResource
    @GetMapping("/all-customer")
    fun getAll(@RequestParam  name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @ApiOperation(value = " Cadastra e salva um customer")
    @ApiResponses(value = [ApiResponse(code = 200, message = "customer criado com sucesso")])
    @PostMapping("/create-customer")
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
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @ApiOperation(value = " Deleta customer pelo ID")
    @ApiResponses(value = [ApiResponse(code = 204, message = "customer deletado")])
    @UserCanOnlyAccessTheirOwnResource
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }

}
