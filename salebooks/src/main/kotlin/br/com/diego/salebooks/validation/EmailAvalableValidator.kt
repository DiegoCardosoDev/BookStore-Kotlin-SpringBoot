package br.com.diego.salebooks.validation

import br.com.diego.salebooks.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvalableValidator(var customerService: CustomerService) : ConstraintValidator<EmailAvalable, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value.isNullOrEmpty()) {
            return false
        }
        return customerService.emailAvailable(value)
    }

}
