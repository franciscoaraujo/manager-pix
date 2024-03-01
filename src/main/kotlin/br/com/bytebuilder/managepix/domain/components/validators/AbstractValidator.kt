package br.com.bytebuilder.managepix.domain.components.validators


import br.com.bytebuilder.managepix.domain.dto.MensagemDto
import br.com.bytebuilder.managepix.domain.dto.PixDto
import br.com.bytebuilder.managepix.domain.enums.TipoChavePix
import br.com.bytebuilder.managepix.domain.utils.DateUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
abstract class AbstractValidator(
    private val emailValidator: EmailValidador,
    private val cnpjValidator: CNPJValidador,
    private val cpfValidator: CPFValidador,
    private val celularValidador: CelularValidador,
    private val chavePixAleatoriaValidador: ChavePixAleatoriaValidador
) {
    protected val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var dateUtils: DateUtils

    open fun validarRegradDePix(pixDTO: PixDto): List<MensagemDto> {
        log.info("Iniciando a validação das regras de pix internamente...")
        val mensagens: MutableList<MensagemDto> = ArrayList<MensagemDto>()

        //validando se é um email válido
        if (pixDTO.getTipoChavePix()
                ?.equals(TipoChavePix.EMAIL) ?: false && !emailValidator.isValidEmail(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDto(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é um cpf válido
        if (pixDTO.getTipoChavePix()?.equals(TipoChavePix.CPF) == true
            && !cpfValidator.isValidCPF(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDto(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é um cnpj válido
        if (pixDTO.getTipoChavePix()?.equals(TipoChavePix.CNPJ) == true
            && !cpfValidator.isValidCPF(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDto(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        if (pixDTO.getTipoChavePix()?.equals(TipoChavePix.CELULAR) == true
            && !celularValidador.isValidCelular(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDto(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é uma chave aleatória valida
        if (pixDTO.getTipoChavePix()?.equals(TipoChavePix.CHAVE_ALEATORIA) == true
            && !chavePixAleatoriaValidador.isValidChavePixAleatoria(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDto(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        log.info("Quantidade de mensagens: {}", mensagens.size)
        log.info("Mensagens: {}", mensagens.toString())
        log.info("Finalizando a validação das regras de chave pix internamente...")
        return mensagens
    }

    companion object {
        const val CD_ESTABELECIMENTO_OBRIGATORIO = "Campo %s é obrigatório"
        const val STATUS_PIX_OBRIGATORIO = "Campo %s é obrigatório"
        const val TIPO_CHAVE_PIX_OBRIGATORIO = "Campo %s é obrigatório"
        const val CHAVE_PIX_OBRIGATORIO = "Campo %s é obrigatório"
        const val DATA_INICIO_VIGENCIA_OBRIGATORIO = "Campo %s é obrigatório"
        const val DATA_FIM_VIGENCIA_OBRIGATORIO = "Campo %s é obrigatório"
        const val DATA_INICIO_VIGENCIA_INVALIDA = "Data %s é inválido"
        const val DATA_FIM_VIGENCIA_INVALIDA = "Data %s é inválido"
        const val TIPO_CHAVE_PIX_IMCOMPATIVEL = "O Tipo de Chave %s é imcompatível com o valor da chave Pix fornecida"
        const val CHAVE_PIX_INVALIDA =
            "Chave Pix %s inválida. Verique se o \'tipoChavePix\' de chave informada é compatível com o valor da \'chavePix\' fornecida"
        const val CHAVE_PIX_CADASTRADA_SUCESSO = "SC001"
        const val COD_CHAVE_PIX_ATIVADA_SUCESSO = "SA002"
        const val CHAVE_PIX_DESATIVADA_SUCESSO = "SD002"
        const val CODIGO_ERRO_VALIDACAO_NIVEL_1 = "E0011"
        const val CODIGO_ERRO_VALIDACAO_NIVEL_2 = "E013"
        fun isValidDate(date: String?): Boolean {
            return try {
                val formattedDate: Date? = DateUtils.stringToDate(date, DateUtils.FORMAT_YYYY_MM_DD)
                formattedDate != null
            } catch (e: Exception) {
                false
            }
        }
    }
}
