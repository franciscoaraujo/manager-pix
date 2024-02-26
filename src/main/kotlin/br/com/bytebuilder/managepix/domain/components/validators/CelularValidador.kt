package br.com.bytebuilder.managepix.domain.components.validators

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class CelularValidador {
    /**
     * Valida se o número de celular fornecido segue o padrão brasileiro.
     * Aceita números com ou sem o código do país, com ou sem parênteses no DDD,
     * e com ou sem espaço ou hífen.
     *
     *
     * Exemplos de formatos aceitos:
     * - (11) 90000-0000
     * - 11900000000
     * - +55 11 90000-0000
     * - +55 (11) 90000-0000
     *
     * @param numeroCelular O número de celular a ser validado.
     * @return true se o número de celular for válido, false caso contrário.
     */
    fun isValidCelular(numeroCelular: String?): Boolean {
        val regex = "^(\\+55)?\\s?(\\(?\\d{2}\\)?)?\\s?(9\\d{4})\\-?\\d{4}$"
        return Pattern.matches(regex, numeroCelular)
    }
}
