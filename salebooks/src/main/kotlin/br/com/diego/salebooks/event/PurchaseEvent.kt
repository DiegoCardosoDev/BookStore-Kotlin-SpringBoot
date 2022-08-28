package br.com.diego.salebooks.event

import br.com.diego.salebooks.models.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(source:Any, purchaseModel: PurchaseModel):ApplicationEvent(source)