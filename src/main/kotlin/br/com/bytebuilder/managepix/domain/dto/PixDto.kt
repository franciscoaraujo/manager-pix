package br.com.bytebuilder.managepix.domain.dto

import br.com.bytebuilder.managepix.domain.entity.PixEntity
import br.com.bytebuilder.managepix.domain.enums.TipoChavePix
import java.math.BigInteger
import java.time.LocalDate

data class PixDto(
    private var cdChavePix: Int,
    private var cdPessoaEstabelecimento: BigInteger,
    private var statusPix: Boolean,
    private var tipoChavePix: TipoChavePix,
    private var chavePix: String,
    private var dtInicioVigencia: LocalDate? = null,
    private var dtFimVigencia: LocalDate? = null,
    private var mensagem: List<MensagemDto>?
) {

    companion object ObjectMapper {
        fun toEntity(pixDTO: PixDto): PixEntity {
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

        fun toDto(pixEntity: PixEntity): PixDto {
            return PixDto(
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

    fun getcdChavePix() = this.cdChavePix

    fun getcdPessoaEstabelecimento() = this.cdPessoaEstabelecimento
    fun getStatusPix() = this.statusPix
    fun getTipoChavePix() = this.tipoChavePix
    fun getChavePix() = this.chavePix
    fun getDtInicioVigencia() = this.dtInicioVigencia
    fun getDtFimVigencia() = this.dtFimVigencia
    fun getMensagem() = this.mensagem

    fun setMensagem(mensagem: List<MensagemDto>?) {
        this.mensagem = mensagem
    }
    fun setStatusPix(statusPix: Boolean) {
        this.statusPix = statusPix
    }
}
