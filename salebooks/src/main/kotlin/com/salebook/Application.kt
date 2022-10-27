package com.salebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableAsync
@EnableWebMvc
@EnableSwagger2
@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
