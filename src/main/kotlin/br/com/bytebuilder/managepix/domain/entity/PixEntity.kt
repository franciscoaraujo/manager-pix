package br.com.bytebuilder.managepix.domain.entity


import br.com.bytebuilder.managepix.domain.enums.TipoChavePix
import jakarta.persistence.*
import java.math.BigInteger
import java.time.LocalDate

@Entity
@Table(name = "pix_entity")
class PixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var cdChavePix: Int? = null
    private var cdPessoaEstabelecimento: BigInteger? = null
    private var statusPix: Boolean? = null
    private var tipoChavePix: TipoChavePix? = null
    private var chavePix: String? = null
    private var dtInicioVigencia: LocalDate? = null
    private var dtFimVigencia: LocalDate? = null

    constructor(
        cdChavePix: Int?,
        cdPessoaEstabelecimento: BigInteger?,
        statusPix: Boolean?,
        tipoChavePix: TipoChavePix?,
        chavePix: String?,
        dtInicioVigencia: LocalDate?,
        dtFimVigencia: LocalDate?
    ) {
        this.cdChavePix = cdChavePix
        this.cdPessoaEstabelecimento = cdPessoaEstabelecimento
        this.statusPix = statusPix
        this.tipoChavePix = tipoChavePix
        this.chavePix = chavePix
        this.dtInicioVigencia = dtInicioVigencia
        this.dtFimVigencia = dtFimVigencia
    }

    constructor()

    fun getcdPessoaEstabelecimento(): BigInteger? {
        return cdPessoaEstabelecimento
    }

    fun getChavePix(): String? {
        return chavePix
    }

    fun getDtFimVigencia(): LocalDate? {
        return dtFimVigencia
    }

    fun getDtInicioVigencia(): LocalDate? {
        return dtInicioVigencia
    }

    fun getTipoChavePix(): TipoChavePix? {
        return tipoChavePix
    }

    fun getStatusPix(): Boolean? {
        return statusPix
    }

    fun getcdChavePix(): Int? {
        return cdChavePix
    }

}
