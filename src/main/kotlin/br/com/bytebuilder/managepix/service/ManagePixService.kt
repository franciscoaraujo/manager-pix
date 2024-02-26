package br.com.bytebuilder.managepix.service

import br.com.bytebuilder.managepix.domain.components.validators.AbstractValidator.Companion.CODIGO_ERRO_VALIDACAO_NIVEL_2
import br.com.bytebuilder.managepix.domain.components.validators.PixValidador
import br.com.bytebuilder.managepix.domain.dto.MensagemDTO
import br.com.bytebuilder.managepix.domain.dto.PixDTO
import br.com.bytebuilder.managepix.domain.entity.PixEntity
import br.com.bytebuilder.managepix.domain.repository.ManagePixRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ManagePixService(private val managePixRepository: ManagePixRepository, private val pixValidador: PixValidador) {

    @Transactional
    fun createPix(pix: PixDTO): PixDTO {

        var mensagens = pixValidador.validarRegradDePix(pix)

        if (mensagens.size > 0) {
            pix.setMensagem(mensagens)
            return pix
        } else {
            pix.getcdPessoaEstabelecimento()?.let {
                managePixRepository.findByCdPessoaEstabelecimentoAndStatusPix(it, true).let {
                    it.setStatusPix(false)
                    managePixRepository.save(it)
                }
            }
            var toEntity = PixDTO.ObjectMapper.toEntity(pix)
            var save = managePixRepository.save(toEntity)
            var toDto = PixDTO.ObjectMapper.toDto(save)
            toDto.setMensagem(Arrays.asList(MensagemDTO("S001", "Chave Pix Cadastra e Ativa com Sucesso.")))
            return toDto
        }
    }
}