package br.com.bytebuilder.managepix.domain.components.validators


import br.com.bytebuilder.managepix.domain.dto.MensagemDTO
import br.com.bytebuilder.managepix.domain.dto.PixDTO
import br.com.bytebuilder.managepix.domain.enums.TipoChavePix
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

@Component
class PixValidador(
    private var emailValidator: EmailValidador,
    private var cnpjValidator: CNPJValidador,
    private var cpfValidator: CPFValidador,
    private var celularValidator: CelularValidador,
    private var chavePixAleatoriaValidator: ChavePixAleatoriaValidador
) : AbstractValidator(
    emailValidator, cnpjValidator, cpfValidator, celularValidator, chavePixAleatoriaValidator
) {

    /**
     * Faz uma validação dos campos obrigatorios em um contexto mais simplificado,
     * indentificando camposque possivelmente podem estar vazios ou nulos e campos obrigatorios
     *
     * @param pixDTO
     * @return
     */
    fun validarSeCamposEstaoNulos(pixDTO: PixDTO): List<MensagemDTO> {

        log.info("Iniciando a validação dos campos...")
        val mensagens: MutableList<MensagemDTO> = ArrayList<MensagemDTO>()

        if (pixDTO.getcdPessoaEstabelecimento() == null) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1,
                    String.format(CD_ESTABELECIMENTO_OBRIGATORIO, "Código do estabelecimento")
                )
            )
        }
        if (pixDTO.getChavePix() == null || pixDTO.getChavePix()?.isBlank() == true) {
            mensagens.add(MensagemDTO(CODIGO_ERRO_VALIDACAO_NIVEL_1, String.format(CHAVE_PIX_OBRIGATORIO, "Chave Pix")))
        }
        if (Objects.isNull(pixDTO.getTipoChavePix()) || pixDTO.getTipoChavePix()?.getDescricao()?.isBlank() == true
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1, String.format(TIPO_CHAVE_PIX_OBRIGATORIO, "Tipo da Chave Pix")
                )
            )
        }
        if (pixDTO.getTipoChavePix() == null || pixDTO.getTipoChavePix()?.getDescricao()?.isBlank() == true) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1, String.format(TIPO_CHAVE_PIX_OBRIGATORIO, "Tipo da Chave Pix")
                )
            )
        }
        if (pixDTO.getStatusPix() == null || java.lang.String.valueOf(pixDTO.getStatusPix())
                .isEmpty() || java.lang.String.valueOf(pixDTO.getStatusPix()) == "null"
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1, String.format(STATUS_PIX_OBRIGATORIO, "Status pix")
                )
            )
        }
        if (pixDTO.getDtInicioVigencia() == null || java.lang.String.valueOf(pixDTO.getDtFimVigencia()).isEmpty()) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1,
                    String.format(DATA_INICIO_VIGENCIA_OBRIGATORIO, "Data inicio da vigência")
                )
            )
        }
        if (pixDTO.getDtFimVigencia() == null || java.lang.String.valueOf(pixDTO.getDtFimVigencia()).isEmpty()) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_1, String.format(DATA_FIM_VIGENCIA_OBRIGATORIO, "Data fim da vigencia")
                )
            )
        }
        log.info("Quantidade de mensagens: {}", mensagens.size)
        log.info("Mensagens: {}", mensagens.toString())
        log.info("Finalizando a validação dos campos...")
        return mensagens
    }

    override fun validarRegradDePix(pixDTO: PixDTO): List<MensagemDTO> {
        log.info("Iniciando a validação das regras de pix internamente...")
        val mensagens: MutableList<MensagemDTO> = ArrayList<MensagemDTO>()
        if (!isValidDate(pixDTO.getDtInicioVigencia().toString())) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(DATA_INICIO_VIGENCIA_INVALIDA, pixDTO.getDtInicioVigencia())
                )
            )
        }
        if (!isValidDate(pixDTO.getDtFimVigencia().toString())) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2,
                    java.lang.String.format(DATA_FIM_VIGENCIA_INVALIDA, pixDTO.getDtFimVigencia())
                )
            )
        }
        //validando se é um email válido
        if (pixDTO.getTipoChavePix()
                ?.equals(TipoChavePix.EMAIL) == true && !emailValidator.isValidEmail(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2, java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é um cpf válido
        if (pixDTO.getTipoChavePix()
                ?.equals(TipoChavePix.CPF) == true && !cpfValidator.isValidCPF(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2, java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é um cnpj válido
        if (pixDTO.getTipoChavePix()?.equals(TipoChavePix.CNPJ) == true && !pixDTO.getChavePix()
                ?.let { cnpjValidator.isValidCNPJ(it) }!!
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2, java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        if (pixDTO.getTipoChavePix()
                ?.equals(TipoChavePix.CELULAR) == true && !celularValidator.isValidCelular(pixDTO.getChavePix())
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2, java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        //validando se é uma chave aleatória valida
        if (pixDTO.getTipoChavePix()
                ?.equals(TipoChavePix.CHAVE_ALEATORIA) == true && !chavePixAleatoriaValidator.isValidChavePixAleatoria(
                pixDTO.getChavePix()
            )
        ) {
            mensagens.add(
                MensagemDTO(
                    CODIGO_ERRO_VALIDACAO_NIVEL_2, java.lang.String.format(CHAVE_PIX_INVALIDA, pixDTO.getChavePix())
                )
            )
        }
        log.info("Quantidade de mensagens: {}", mensagens.size)
        log.info("Mensagens: {}", mensagens.toString())
        log.info("Finalizando a validação das regras de chave pix internamente...")
        return mensagens
    }
}
