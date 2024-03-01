package br.com.bytebuilder.managepix.domain.dto

import java.math.BigInteger


class EstabelecimentoDto {

    private var cdEstabelecimento: BigInteger
    private var nomeEstabelecimento: String
    private var EnderecoDto: EnderecoDto

    constructor(cdEstabelecimento: BigInteger, nomeEstabelecimento: String, EnderecoDto: EnderecoDto) {
        this.cdEstabelecimento = cdEstabelecimento
        this.nomeEstabelecimento = nomeEstabelecimento
        this.EnderecoDto = EnderecoDto
    }

    //getters
    fun getCdEstabelecimento() = this.cdEstabelecimento
    fun getEnderecoDto() = this.EnderecoDto

    fun getNomeEstabelecimento() = this.nomeEstabelecimento

}