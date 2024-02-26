package br.com.bytebuilder.managepix.domain.components.validators

import org.springframework.stereotype.Component

/**
 * Validates the given CNPJ number.
 *
 * @param cnpj the CNPJ to be validated
 * @return true if the CNPJ is valid, false otherwise
 */
@Component
class CNPJValidador {
    fun isValidCNPJ(cnpj: String): Boolean {
        var cnpj = cnpj
        if (cnpj == "00000000000000") {
            return false
        }
        if (cnpj == "00000000000000"
            || cnpj == "11111111111111"
            || cnpj == "22222222222222"
            || cnpj == "33333333333333"
            || cnpj == "44444444444444"
            || cnpj == "55555555555555"
            || cnpj == "66666666666666"
            || cnpj == "77777777777777"
            || cnpj == "88888888888888"
            || cnpj == "99999999999999"
        ) {
            return false
        }
        cnpj = cnpj.replace("[^\\d]".toRegex(), "")
        if (cnpj.length != 14) {
            return false
        }
        if (cnpj.matches("(\\d)\\1{13}".toRegex())) {
            return false
        }
        if (cnpj.matches((cnpj[0].toString() + "{14}").toRegex())) {
            return false
        }
        val cnpjArray = cnpj.toCharArray()
        val weights1 = intArrayOf(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        val weights2 = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)
        var sum = 0
        for (i in 0..11) {
            sum += (cnpj[i].code - '0'.code) * weights1[i]
        }
        var remainder = sum % 11
        val digit1 = if (remainder < 2) 0 else 11 - remainder
        sum = 0
        for (i in 0..12) {
            sum += (cnpj[i].code - '0'.code) * weights2[i]
        }
        remainder = sum % 11
        val digit2 = if (remainder < 2) 0 else 11 - remainder
        return digit1 == cnpj[12].code - '0'.code && digit2 == cnpj[13].code - '0'.code
    }
}
