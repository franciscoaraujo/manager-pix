package br.com.bytebuilder.managepix.domain.dto

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "tipo_estabelecimento")
class TipoEstabelecimentoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cdTipoEstabelecimento: BigInteger
    var nomeTipoEstabelecimento: String

    constructor(
        cdTipoEstabelecimento: BigInteger,
        nomeTipoEstabelecimento: String
    ) {
        this.cdTipoEstabelecimento = cdTipoEstabelecimento
        this.nomeTipoEstabelecimento = nomeTipoEstabelecimento
    }

    fun getNomeTipoEstabelecimento() = this.nomeTipoEstabelecimento

    fun getCdTipoEstabelecimento() = this.cdTipoEstabelecimento

}