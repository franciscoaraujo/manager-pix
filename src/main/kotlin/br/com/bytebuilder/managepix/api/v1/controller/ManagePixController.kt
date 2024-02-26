package br.com.bytebuilder.managepix.api.v1.controller

import br.com.bytebuilder.managepix.domain.dto.PixDTO
import br.com.bytebuilder.managepix.service.ManagePixService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class ManagePixController (private val managePixService: ManagePixService) {

    @GetMapping
    fun hello() = "It's work"

    @PostMapping("cadastro")
    fun createPix(@RequestBody  pix: PixDTO): PixDTO {
        return managePixService.createPix(pix)
    }
}