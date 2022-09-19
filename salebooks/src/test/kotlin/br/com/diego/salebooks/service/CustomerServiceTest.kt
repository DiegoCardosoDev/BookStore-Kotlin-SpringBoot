package br.com.diego.salebooks.service

import br.com.diego.salebooks.enums.CustomerStatus
import br.com.diego.salebooks.enums.Role
import br.com.diego.salebooks.exeptions.NotFoundExeption
import br.com.diego.salebooks.models.CustomerModel
import br.com.diego.salebooks.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
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
 fun `should return  customer id `() {
  val id = Random().nextInt()
  val fakeCustomer = buildCustomer(id = id)
  every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
  val customer = customerService.findById(id)
  assertEquals(fakeCustomer, customer)
  verify(exactly = 1) { customerRepository.findById(id) }
 }

 @Test
 fun `should  throw  error  customer not found `() {
  val id = Random().nextInt()
  every { customerRepository.findById(id) } returns Optional.empty()
  val error = assertThrows<NotFoundExeption>{ customerService.findById(id) }

  assertEquals("Book [${id}] not exists", error.message)
  assertEquals("ML-101", error.erroCode)
  verify(exactly = 1) { customerRepository.findById(id) }

 }


 fun buildCustomer(id: Int? = null,
                   name: String = "customerName",
                   email: String = "${UUID.randomUUID()}@email.com",
                   password: String = "pass"
 ) = CustomerModel(
         id = id,
         name = name,
         email = email,
         status = CustomerStatus.ATIVO,
         password = password,
         roles = setOf(Role.CUSTOMER)
 )

}