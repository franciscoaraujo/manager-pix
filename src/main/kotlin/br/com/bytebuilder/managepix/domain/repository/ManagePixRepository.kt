package br.com.bytebuilder.managepix.domain.repository

import br.com.bytebuilder.managepix.domain.entity.PixEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagePixRepository : JpaRepository<PixEntity, Int>{

    fun findByCdPessoaEstabelecimento(cdPessoaEstabelecimento: Int): PixEntity
}