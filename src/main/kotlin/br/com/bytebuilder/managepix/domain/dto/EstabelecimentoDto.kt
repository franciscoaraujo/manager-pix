package br.com.bytebuilder.managepix.domain.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigInteger


@Entity
class EstabelecimentoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cdEstabelecimento: BigInteger
    private var nomeEstabelecimento: String
    private var EnderecoDto: EnderecoDto
    private var tipoEstabelecimento: TipoEstabelecimentoDto

    constructor(cdEstabelecimento: BigInteger, nomeEstabelecimento: String, EnderecoDto: EnderecoDto, tipoEstabelecimento: TipoEstabelecimentoDto) {
        this.cdEstabelecimento = cdEstabelecimento
        this.nomeEstabelecimento = nomeEstabelecimento
        this.EnderecoDto = EnderecoDto
        this.tipoEstabelecimento = tipoEstabelecimento
    }

    //getters
    fun getCdEstabelecimento() = this.cdEstabelecimento
    fun getEnderecoDto() = this.EnderecoDto

    fun getNomeEstabelecimento() = this.nomeEstabelecimento

}