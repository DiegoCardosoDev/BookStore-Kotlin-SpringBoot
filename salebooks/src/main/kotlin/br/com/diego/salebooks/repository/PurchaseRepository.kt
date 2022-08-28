package br.com.diego.salebooks.repository

import br.com.diego.salebooks.models.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository:CrudRepository<PurchaseModel, Int> {

}
