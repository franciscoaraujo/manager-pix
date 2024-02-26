package br.com.bytebuilder.managepix.domain.repository

import br.com.bytebuilder.managepix.domain.entity.PixEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface ManagePixRepository : JpaRepository<PixEntity, BigInteger> {

    fun findByCdPessoaEstabelecimento(cdPessoaEstabelecimento: BigInteger): PixEntity

    fun findByCdPessoaEstabelecimentoAndStatusPix(cdPessoaEstabelecimento: BigInteger, statusPix: Boolean): PixEntity
}