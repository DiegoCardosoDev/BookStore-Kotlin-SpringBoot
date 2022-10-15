package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.enums.Errors
import br.com.diego.salebooks.exeptions.NotFoundExeption
import br.com.diego.salebooks.helper.buildCustomer
import br.com.diego.salebooks.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @InjectMockKs
    @SpyK
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {

        val fakeCustomers = listOf(buildCustomer(), buildCustomer())
        every { customerRepository.findAll() } returns fakeCustomers
        val customers = customerService.getAll(null)
        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContainingIgnoreCase(any()) }

    }

    @Test
    fun `should return customers when is name infomed`() {

        val name = UUID.randomUUID().toString()
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContainingIgnoreCase(name) } returns fakeCustomers
        val customers = customerService.getAll(name)
        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 1) { customerRepository.findByNameContainingIgnoreCase(name) }

    }

    @Test
    fun `should create customer and encrypt password`() {
        val initPass = Math.random().toString()
        val fakeCustomer = buildCustomer(password = initPass)
        val fakePass = UUID.randomUUID().toString()
        val fakeEncrypt = fakeCustomer.copy(password = fakePass)
        every { customerRepository.save(fakeEncrypt) } returns fakeCustomer
        every { bCryptPasswordEncoder.encode(initPass) } returns fakePass
        customerService.create(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeEncrypt) }
        verify(exactly = 1) { bCryptPasswordEncoder.encode(initPass) }
    }

    @Test
    fun `should return  customer by id `() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
        val customer = customerService.findById(id)
        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should   throw  not found when find by id `() {
        val id = Random().nextInt()
        every { customerRepository.findById(id) } returns Optional.empty()
        val error = assertThrows<NotFoundExeption> { customerService.findById(id) }

        assertEquals("Book [${id}] not exists", error.message)
        assertEquals("ML-101", error.erroCode)
        verify(exactly = 1) { customerRepository.findById(id) }

    }

    @Test
    fun `should update customer `() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        every { customerRepository.existsById(id) } returns true
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer
        customerService.update(fakeCustomer)
        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }

    }

    @Test
    fun `should throw  update customer  `() {

        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns false
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        val error = assertThrows<NotFoundExeption> { customerService.update(fakeCustomer) }

        assertEquals("Book [${id}] not exists", error.message)
        assertEquals("ML-101", error.erroCode)

        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 0) { customerRepository.save(any()) }

    }

    @Test
    fun `should throw  delete customer  `() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        val expectedCustomer = fakeCustomer.copy(status = CustomerStatus.INATIVO)
        every { customerService.findById(id) } returns fakeCustomer
        every { customerRepository.save(expectedCustomer) } returns expectedCustomer
        every { bookService.deleteByCustomer(fakeCustomer) } just runs
        customerService.delete(id)
        verify(exactly = 1) { customerService.findById(id) }
        verify(exactly = 1) { bookService.deleteByCustomer(fakeCustomer) }
        verify(exactly = 1) { customerRepository.save(expectedCustomer) }
    }

    @Test
    fun `should throw not found exception when  delete customer  `() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        every { customerService.findById(id) } throws NotFoundExeption(Errors.ML101.message.format(id), Errors.ML101.code)
        val error = assertThrows<NotFoundExeption> { customerService.findById(id) }
        assertEquals("Book [${id}] not exists", error.message)
        assertEquals("ML-101", error.erroCode)

        verify(exactly = 1) { customerService.findById(id) }
        verify(exactly = 0) { bookService.deleteByCustomer(fakeCustomer) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

    @Test
    fun `should return true when email available `() {
        val email = "${Random().nextInt().toString()}@email.com"
        every { customerRepository.existsByEmail(email) } returns false
        val emailAvailable = customerService.emailAvailable(email)
        assertTrue(emailAvailable)
        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }

    @Test
    fun `should return false when email unavailable `() {
        val email = "${Random().nextInt().toString()}@email.com"
        every { customerRepository.existsByEmail(email) } returns true
        val emailAvailable = customerService.emailAvailable(email)
        assertFalse(emailAvailable)
        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }


}