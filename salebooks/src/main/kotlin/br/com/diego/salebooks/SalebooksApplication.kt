package br.com.diego.salebooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableAsync
@EnableWebMvc
@SpringBootApplication
@EnableSwagger2
class SalebooksApplication

fun main(args: Array<String>) {
	runApplication<SalebooksApplication>(*args)
}
