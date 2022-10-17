package br.com.diego.salebooks.security

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.models.CustomerModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetail(private val customerModel: CustomerModel):UserDetails {

    val id:Int=customerModel.id!!

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        customerModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()


    override fun getPassword(): String=customerModel.password

    override fun getUsername(): String =customerModel.id.toString()

    override fun isAccountNonExpired(): Boolean =true

    override fun isAccountNonLocked(): Boolean =true

    override fun isCredentialsNonExpired(): Boolean =true

    override fun isEnabled(): Boolean = customerModel.status==CustomerStatus.ATIVO
}