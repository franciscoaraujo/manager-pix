package br.com.bytebuilder.managepix.domain.dto

import br.com.bytebuilder.managepix.domain.entity.PixEntity
import br.com.bytebuilder.managepix.domain.enums.TipoChavePix
import java.math.BigInteger
import java.time.LocalDate

data class PixDTO(
    private var cdChavePix: Int?,
    private var cdPessoaEstabelecimento: BigInteger?,
    private var statusPix: Boolean?,
    private var tipoChavePix: TipoChavePix?,
    private var chavePix: String?,
    private var dtInicioVigencia: LocalDate?,
    private var dtFimVigencia: LocalDate?,
    private var mensagem: List<MensagemDTO>?
) {

    companion object ObjectMapper {

        fun toEntity(pixDTO: PixDTO): PixEntity {
            return PixEntity(
                pixDTO.cdChavePix,
                pixDTO.cdPessoaEstabelecimento,
                pixDTO.statusPix,
                pixDTO.tipoChavePix,
                pixDTO.chavePix,
                pixDTO.dtInicioVigencia,
                pixDTO.dtFimVigencia
            )
        }

        fun toDto(pixEntity: PixEntity): PixDTO {
            return PixDTO(
                pixEntity.getcdChavePix(),
                pixEntity.getcdPessoaEstabelecimento(),
                pixEntity.getStatusPix(),
                pixEntity.getTipoChavePix(),
                pixEntity.getChavePix(),
                pixEntity.getDtInicioVigencia(),
                pixEntity.getDtFimVigencia(),
                null
            )
        }
    }
    //getters

    fun getcdChavePix(): Int? {
        return cdChavePix
    }

    fun getcdPessoaEstabelecimento(): BigInteger? {
        return cdPessoaEstabelecimento
    }

    fun getStatusPix(): Boolean? {
        return statusPix
    }

    fun getTipoChavePix(): TipoChavePix? {
        return tipoChavePix
    }

    fun getChavePix(): String? {
        return chavePix
    }

    fun getDtInicioVigencia(): LocalDate? {
        return dtInicioVigencia
    }

    fun getDtFimVigencia(): LocalDate? {
        return dtFimVigencia
    }

    fun getMensagem(): List<MensagemDTO>? {
        return mensagem
    }

    fun setMensagem(mensagem: List<MensagemDTO>?) {
        this.mensagem = mensagem
    }
}
