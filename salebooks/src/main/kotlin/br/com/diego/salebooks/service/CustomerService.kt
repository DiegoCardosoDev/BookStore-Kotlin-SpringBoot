package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.enums.Errors
import br.com.diego.salebooks.enums.Role
import br.com.diego.salebooks.exeptions.NotFoundExeption
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class CustomerService(
        private val customerRepository: CustomerRepository,
        private val bookService: BookService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContainingIgnoreCase(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        val customerCopy=customer.copy(
                roles = setOf(Role.CUSTOMER),
                password = bCryptPasswordEncoder.encode(customer.password)

        )
        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow { NotFoundExeption(Errors.ML101.message.format(id), Errors.ML101.code) }
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw NotFoundExeption(Errors.ML101.message.format(customer.id), Errors.ML101.code)
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
