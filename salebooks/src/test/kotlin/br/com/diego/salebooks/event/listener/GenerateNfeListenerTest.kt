package br.com.diego.salebooks.event.listener

import br.com.diego.salebooks.event.PurchaseEvent
import br.com.diego.salebooks.helper.buildPurchase
import br.com.diego.salebooks.service.PurchaseService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
 class GenerateNfeListenerTest{


    @MockK
    private lateinit var purchaseService: PurchaseService

    @InjectMockKs
    private lateinit var generateNfeListener: GenerateNfeListener


    @Test
    fun `should generate nfe`(){

        val purchase = buildPurchase(nfe = null )
        val nfeFake = UUID.randomUUID()
        val purchaseExected= purchase.copy(nfe = nfeFake.toString())

        mockkStatic(UUID::class)
        every { UUID.randomUUID() }returns nfeFake
        every { purchaseService.update(purchaseExected) } just  runs
        generateNfeListener.listen(PurchaseEvent(this, purchase))
        verify (exactly = 1){purchaseService.update(purchaseExected)  }


    }
 }