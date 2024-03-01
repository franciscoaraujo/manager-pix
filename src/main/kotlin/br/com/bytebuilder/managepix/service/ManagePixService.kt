package br.com.bytebuilder.managepix.service

import br.com.bytebuilder.managepix.domain.components.validators.PixValidador
import br.com.bytebuilder.managepix.domain.dto.MensagemDto
import br.com.bytebuilder.managepix.domain.dto.PixDto
import br.com.bytebuilder.managepix.domain.repository.ManagePixRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.*

@Service
class ManagePixService(private val managePixRepository: ManagePixRepository, private val pixValidador: PixValidador) {

    @Transactional
    fun createPix(pix: PixDto): PixDto {
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
            var toEntity = PixDto.ObjectMapper.toEntity(pix)
            var save = managePixRepository.save(toEntity)
            var toDto = PixDto.ObjectMapper.toDto(save)
            toDto.setMensagem(Arrays.asList(MensagemDto("S001", "Chave Pix Cadastra e Ativa com Sucesso.")))
            return toDto
        }
    }

    fun recoveryPix(cdPessoaEstabelecimento: BigInteger): List<PixDto> {
        val listPixReturn = mutableListOf<PixDto>()
        managePixRepository.findByCdPessoaEstabelecimento(cdPessoaEstabelecimento).let {
            it.forEach { entityOut ->
                val toDto = PixDto.ObjectMapper.toDto(entityOut)
                listPixReturn.add(toDto)
            }
        }
        return listPixReturn;
    }
}