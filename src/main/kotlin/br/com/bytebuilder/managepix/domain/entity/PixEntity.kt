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
    private var cdChavePix: Int
    private var cdPessoaEstabelecimento: BigInteger
    private var statusPix: Boolean
    private var tipoChavePix: TipoChavePix
    private var chavePix: String
    private var dtInicioVigencia: LocalDate? = null
    private var dtFimVigencia: LocalDate? = null

    constructor(
        cdChavePix: Int,
        cdPessoaEstabelecimento: BigInteger,
        statusPix: Boolean,
        tipoChavePix: TipoChavePix,
        chavePix: String,
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

    fun getcdPessoaEstabelecimento() = this.cdPessoaEstabelecimento

    fun getcdChavePix() = this.cdChavePix

    fun getStatusPix() = this.statusPix

    fun getTipoChavePix() = this.tipoChavePix

    fun getChavePix() = this.chavePix

    fun getDtFimVigencia() = this.dtFimVigencia

    fun getDtInicioVigencia() = this.dtInicioVigencia

    fun setStatusPix(statusPix: Boolean) {
        this.statusPix = statusPix
    }
}
