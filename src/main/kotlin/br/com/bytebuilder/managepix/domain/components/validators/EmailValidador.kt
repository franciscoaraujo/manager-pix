package br.com.bytebuilder.managepix.domain.components.validators

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class EmailValidador {
    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+(\\.com|\\.com\\.br)$"
    private val pattern: Pattern

    init {
        pattern = Pattern.compile(EMAIL_REGEX)
    }

    /**
     * Valida um endereço de e-mail com base na expressão regular.
     *
     * @param email O e-mail a ser validado.
     * @return true se o e-mail for válido, false caso contrário.
     */
    fun isValidEmail(email: String?): Boolean {
        if (email == null) {
            return false
        }
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
