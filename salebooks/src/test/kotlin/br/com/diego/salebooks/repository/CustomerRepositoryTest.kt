package br.com.diego.salebooks.repository

import br.com.diego.salebooks.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup()= customerRepository.deleteAll()

    @Nested
    inner class `exists by name`{
        @Test
        fun `should return name containing`(){
            val marcos = customerRepository.save(buildCustomer(name = "Diego"))
            val mateus = customerRepository.save(buildCustomer(name = "Diogo"))
            customerRepository.save(buildCustomer(name = "pamela"))
            customerRepository.save(buildCustomer(name = "jose"))

            val customers = customerRepository.findByNameContainingIgnoreCase("Di")
            assertEquals(listOf(marcos,mateus),customers)

        }

    }


    @Nested
    inner class `exists by email`{
        @Test
        fun `should return true when email exists`(){
            val email= "email@teste.com"
            val marcos = customerRepository.save(buildCustomer(email=email))
            val existsByEmail = customerRepository.existsByEmail(email)
            assertTrue(existsByEmail)
        }

        @Test
        fun `should return false when email exists`(){
            val email = "emailnaoexiste@teste.com"
            val existsByEmail = customerRepository.existsByEmail(email)
            assertFalse(existsByEmail)
        }

    }



    @Nested
    inner class `find by email`{
        @Test
        fun `should customer  when email exists`(){
            val email= "email@teste.com"
            val customer = customerRepository.save(buildCustomer(email=email))
            val result = customerRepository.findByEmail(email)
            assertNotNull(result)
            assertEquals(customer, result)
        }

        @Test
        fun `should return customer when email do not exists`(){
            val email = "emailnaoexiste@teste.com"
            val result = customerRepository.findByEmail(email)
            assertNull(result)
        }

    }

}