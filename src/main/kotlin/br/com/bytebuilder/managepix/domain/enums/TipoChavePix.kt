package br.com.bytebuilder.managepix.domain.enums

import java.util.*


enum class TipoChavePix(private val id: Int, private val descricao: String) {

    EMAIL(0, "email"),
    CELULAR(1, "celular"),
    CPF(2, "cpf"),
    CNPJ(3, "cnpj"),
    CHAVE_ALEATORIA(4, "chave_aleatoria");

    companion object {
        @Throws(ValorEnumInvalido::class)
        fun fromValue(id: Int): TipoChavePix {
            return Arrays.stream(entries.toTypedArray())
                .filter { v: TipoChavePix -> v.id == id }
                .findFirst()
                .orElseThrow { ValorEnumInvalido("Invalid Value Id ($id) for Enum Conversion") }
        }
    }

    //getters

    fun getId(): Int {
        return id
    }

    fun getDescricao(): String {
        return descricao
    }
}
