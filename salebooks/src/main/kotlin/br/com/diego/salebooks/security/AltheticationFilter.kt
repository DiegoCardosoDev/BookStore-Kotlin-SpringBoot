package br.com.diego.salebooks.security

import br.com.diego.salebooks.controller.request.LoginRequest
import br.com.diego.salebooks.exeptions.AutheticationExeption
import br.com.diego.salebooks.repository.CustomerRepository
import br.com.diego.salebooks.security.util.JwtUltil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AltheticationFilter(
        private val authManager: AuthenticationManager,
        private val customerRepository: CustomerRepository,
        private val jwtUltil: JwtUltil
):UsernamePasswordAuthenticationFilter() {


    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = customerRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id, loginRequest.password)
            return authManager.authenticate(authToken)

        }catch (ex:Exception){
            throw AutheticationExeption("falha ao se autenticar", "999")

        }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val id =(authResult.principal as UserCustomDetail).id
        val token = jwtUltil.generateToken(id)
        response.addHeader("Authorization", "Bearer $token")

    }
}