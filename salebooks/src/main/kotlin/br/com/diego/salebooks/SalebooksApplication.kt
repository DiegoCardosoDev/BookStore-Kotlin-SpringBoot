package br.com.diego.salebooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SalebooksApplication

fun main(args: Array<String>) {
	runApplication<SalebooksApplication>(*args)
}
