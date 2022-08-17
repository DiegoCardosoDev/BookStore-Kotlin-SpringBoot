package br.com.diego.salebooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class SalebooksApplication

fun main(args: Array<String>) {
	runApplication<SalebooksApplication>(*args)
}
