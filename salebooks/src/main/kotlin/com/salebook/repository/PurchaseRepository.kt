package com.salebook.repository

import com.salebook.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {

}
