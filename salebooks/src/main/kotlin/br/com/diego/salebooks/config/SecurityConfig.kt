package br.com.diego.salebooks.config

import br.com.diego.salebooks.repository.CustomerRepository
import br.com.diego.salebooks.security.AltheticationFilter
import br.com.diego.salebooks.security.AuthorizationFilter
import br.com.diego.salebooks.security.util.JwtUltil
import br.com.diego.salebooks.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val customerRepository: CustomerRepository,
        private val userDetails: UserDetailsCustomService,
        private val jwtUltil: JwtUltil
):WebSecurityConfigurerAdapter() {

    private val PUBLIC_POST_MATCHERS= arrayOf(
            "/customer"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                .anyRequest()
                .authenticated()
        http.addFilter(AltheticationFilter(authenticationManager(),customerRepository,jwtUltil))
        http.addFilter(AuthorizationFilter(authenticationManager(),userDetails,jwtUltil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }
    @Bean
    fun bCryptPasswordEncoder():BCryptPasswordEncoder{
        return BCryptPasswordEncoder(   )
    }
}