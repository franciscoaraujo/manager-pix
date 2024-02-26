package br.com.bytebuilder.managepix.service

import br.com.bytebuilder.managepix.domain.components.validators.PixValidador
import br.com.bytebuilder.managepix.domain.dto.PixDTO
import br.com.bytebuilder.managepix.domain.entity.PixEntity
import br.com.bytebuilder.managepix.domain.repository.ManagePixRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ManagePixService (private val managePixRepository: ManagePixRepository, private val pixValidador: PixValidador) {

    @Transactional
    fun createPix(pix: PixDTO):PixDTO {
        var mensagens = pixValidador.validarRegradDePix(pix)
        if (mensagens.size > 0) {
            pix.setMensagem( mensagens )
            return pix
        }
        var toEntity = PixDTO.ObjectMapper.toEntity(pix)
        var save = managePixRepository.save(toEntity)
        return PixDTO.ObjectMapper.toDto(save)
    }

}