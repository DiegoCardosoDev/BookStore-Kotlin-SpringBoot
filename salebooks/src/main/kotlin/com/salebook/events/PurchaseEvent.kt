package com.salebook.events

import com.salebook.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
): ApplicationEvent(source)