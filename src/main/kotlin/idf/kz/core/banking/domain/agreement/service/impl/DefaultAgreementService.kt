package idf.kz.core.banking.domain.agreement.service.impl

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.core.banking.domain.agreement.mapper.AgreementMapper
import idf.kz.core.banking.domain.agreement.repository.api.AgreementRepository
import idf.kz.core.banking.domain.agreement.service.api.AgreementService
import idf.kz.library.mapper.Mapper
import idf.kz.library.service.AbstractService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class DefaultAgreementService(
  private val agreementRepository: AgreementRepository,
  private val agreementMapper: AgreementMapper
) :
  AbstractService<AgreementDto, Agreement, AgreementRepository, Mapper<Agreement, AgreementDto>>(
    agreementRepository,
    agreementMapper
  ), AgreementService {
  override fun findByName(name: String): Flux<Agreement> =  agreementRepository.findByName(name)
}