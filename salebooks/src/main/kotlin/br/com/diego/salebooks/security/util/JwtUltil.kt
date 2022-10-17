package br.com.diego.salebooks.security.util

import br.com.diego.salebooks.exeptions.AutheticationExeption
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value
import java.util.*

@Component
class JwtUltil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id:Int): String? {
        return  Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(Date(System.currentTimeMillis()  + expiration!!))
                .signWith(SignatureAlgorithm.HS512,secret!!.toByteArray())
                .compact()

    }

    fun isValidToken(token: String): Boolean {
        val clains = getClaims(token)
        if (clains.subject==null || clains.expiration==null || Date().after(clains.expiration)){
            return false
        }
        return true

    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        }catch (ex:Exception){
            throw AutheticationExeption("token inválido", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }
}