package br.com.diego.salebooks.exeptions

import org.flywaydb.core.api.ErrorCode

class AutheticationExeption(override val message:String, errorCode: String):Exception(){
}