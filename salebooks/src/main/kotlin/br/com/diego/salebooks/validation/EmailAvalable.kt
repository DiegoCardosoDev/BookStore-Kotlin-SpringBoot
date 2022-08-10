package br.com.diego.salebooks.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvalableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvalable(
        val message:String = "email já cadasrado",
        val groups:Array<KClass<*>> = [],
        val  payload: Array<KClass<out Payload>> = []
)
