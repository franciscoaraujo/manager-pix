package br.com.bytebuilder.managepix.domain.dto

class EnderecoDto {

   private var cep: String
   private var logradouro: String
   private var complemento: String
   private var bairro: String
   private var localidade: String
   private var uf: String

    constructor(cep: String, logradouro: String, complemento: String, bairro: String, localidade: String, uf: String) {
        this.cep = cep
        this.logradouro = logradouro
        this.complemento = complemento
        this.bairro = bairro
        this.localidade = localidade
        this.uf = uf
    }

    //getters
    fun getCep() = this.cep
    fun getLogradouro() = this.logradouro
    fun getComplemento() = this.complemento
    fun getBairro() = this.bairro
    fun getLocalidade() = this.localidade
    fun getUf() = this.uf

}