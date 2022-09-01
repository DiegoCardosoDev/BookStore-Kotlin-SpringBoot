package br.com.diego.salebooks.service

import br.com.diego.salebooks.exeptions.AutheticationExeption
import br.com.diego.salebooks.repository.CustomerRepository
import br.com.diego.salebooks.security.UserCustomDetail
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
        private val customerRepository: CustomerRepository
):UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
                .orElseThrow{AutheticationExeption("usuário não encontrado","999")}
        return UserCustomDetail(customer)
    }
}