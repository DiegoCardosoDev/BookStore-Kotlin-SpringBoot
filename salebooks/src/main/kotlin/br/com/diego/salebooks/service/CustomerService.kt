package br.com.diego.salebooks.service

import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    /*GET ALL*/
    fun getAll(name: String?): List<CustomerModel> {

        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(name)
        }
        return customerRepository.findAll().toList()
    }


    /*CREATE*/
    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    /*GETID*/
    fun getById(id: Int): CustomerModel {
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
        val customer = getById(id)
        bookService.deleteByCustumer(customer)
        customerRepository.deleteById(id)
    }

}
