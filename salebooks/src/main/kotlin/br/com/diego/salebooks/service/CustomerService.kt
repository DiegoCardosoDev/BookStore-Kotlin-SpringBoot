package br.com.diego.salebooks.service

import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import org.springframework.stereotype.Service
import kotlin.math.E

@Service
class CustomerService(val customerRepository: CustomerRepository) {

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
    fun getCustomer(id: Int): CustomerModel {
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

       if (!customerRepository.existsById(id)){
           throw Exception()
       }
        customerRepository.deleteById(id)
    }

}
