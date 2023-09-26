package idf.kz.core.banking.domain.agreement.service.api

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.core.banking.domain.agreement.mapper.AgreementMapper
import idf.kz.core.banking.domain.agreement.repository.api.AgreementRepository
import idf.kz.library.mapper.Mapper
import idf.kz.library.service.AbstractService
import idf.kz.library.service.Service
import reactor.core.publisher.Flux

interface AgreementService: Service<AgreementDto>{
  fun findByName(name: String): Flux<Agreement>
}