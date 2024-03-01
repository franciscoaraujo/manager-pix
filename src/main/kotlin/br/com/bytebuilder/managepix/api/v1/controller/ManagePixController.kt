package br.com.bytebuilder.managepix.api.v1.controller

import br.com.bytebuilder.managepix.domain.dto.PixDto
import br.com.bytebuilder.managepix.service.ManagePixService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController
@RequestMapping("/api/v1/")
class ManagePixController (private val managePixService: ManagePixService) {

    @GetMapping
    fun hello() = "It's work"

    @PostMapping("cadastro")
    fun createPix(@RequestBody  pix: PixDto): PixDto {
        return managePixService.createPix(pix)
    }

    @GetMapping("listar/{cdPessoalEstabelecimento}")
    fun recoveryAllPixByEC(@PathVariable cdPessoalEstabelecimento: BigInteger): List<PixDto>{
        return managePixService.recoveryPix(cdPessoalEstabelecimento)
    }
}