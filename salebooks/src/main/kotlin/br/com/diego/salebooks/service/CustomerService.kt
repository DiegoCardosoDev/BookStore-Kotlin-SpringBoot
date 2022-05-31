package br.com.diego.salebooks.service

import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.models.status.customerStatus
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    /*GET ALL*/
    fun getAll(pageable: Pageable): Page<CustomerModel> {
        return customerRepository.findAll(pageable)
    }


    /*CREATE*/
    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    /*GETID*/
    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    /*UPDATE*/
    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    /*DELETE*/
    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustumer(customer)
        customer.status = customerStatus.INATIVO
        customerRepository.save(customer)
    }

}
