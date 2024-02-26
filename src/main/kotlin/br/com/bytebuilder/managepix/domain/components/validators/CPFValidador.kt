package br.com.bytebuilder.managepix.domain.components.validators

import org.springframework.stereotype.Component

@Component
class CPFValidador {
    fun isValidCPF(cpf: String?): Boolean {
        var cpf = cpf ?: return false
        cpf = cpf.replace("[^\\d]".toRegex(), "") // Remove caracteres não numéricos
        if (cpf.length != 11 || cpf.chars().distinct().count() == 1L) {
            return false // CPF inválido se não tiver 11 dígitos ou todos dígitos iguais
        }
        val weightsFirstDigit = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2)
        val weightsSecondDigit = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
        val firstDigit = calculateDigit(cpf, weightsFirstDigit)
        val secondDigit = calculateDigit(cpf, weightsSecondDigit)
        return cpf == cpf.substring(0, 9) + firstDigit + secondDigit
    }

    companion object {
        private fun calculateDigit(str: String, weights: IntArray): Int {
            var sum = 0
            for (index in weights.indices) {
                val num = (str[index].code - '0'.code) * weights[index]
                sum += num
            }
            val remainder = sum % 11
            return if (remainder < 2) 0 else 11 - remainder
        }
    }
}
