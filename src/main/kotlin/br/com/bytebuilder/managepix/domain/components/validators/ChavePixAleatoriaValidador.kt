package br.com.bytebuilder.managepix.domain.components.validators

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class ChavePixAleatoriaValidador {
    /**
     * Valida se a string fornecida é uma chave Pix aleatória válida.
     * Uma chave Pix aleatória válida deve ter exatamente 32 caracteres,
     * incluindo letras (maiúsculas e minúsculas), dígitos e caracteres especiais permitidos.
     *
     * @param chavePix A chave Pix a ser validada.
     * @return true se a chave Pix for válida, false caso contrário.
     */
    fun isValidChavePixAleatoria(chavePix: String?): Boolean {
        // Regex para validar a chave Pix aleatória
        // Considera letras, números e alguns caracteres especiais permitidos
        val regex = "^[A-Za-z0-9+\\/=\\-]{32}$"
        return Pattern.matches(regex, chavePix)
    }
}
