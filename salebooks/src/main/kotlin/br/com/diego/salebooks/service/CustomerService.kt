package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.enums.Errors
import br.com.diego.salebooks.exeptions.NotFoundExeption
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.stereotype.Service


@Service
class CustomerService(
        val customerRepository: CustomerRepository,
        val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow { NotFoundExeption(Errors.BK201.message.format(id), Errors.BK201.code) }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)

    }

}
