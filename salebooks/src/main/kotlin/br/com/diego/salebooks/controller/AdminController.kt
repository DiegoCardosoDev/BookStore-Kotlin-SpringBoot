package br.com.diego.salebooks.controller

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping("admin")
class AdminController(
) {

    @ApiOperation(value = " Carrega todos customers")
    @ApiResponses(value = [ApiResponse(code = 200, message = "customer carregado com sucesso!")])
    @GetMapping("/report")
    fun report(): String{
        return "This is  a Report. Only Admin can see it"
    }



}
