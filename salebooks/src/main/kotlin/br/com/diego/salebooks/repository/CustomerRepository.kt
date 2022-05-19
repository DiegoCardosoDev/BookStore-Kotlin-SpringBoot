package br.com.diego.salebooks.repository

import br.com.diego.salebooks.models.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Long>