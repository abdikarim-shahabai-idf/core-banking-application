package idf.kz.core.banking.domain.agreement.service.impl

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.core.banking.domain.agreement.mapper.DefaultAgreementMapper
import idf.kz.core.banking.domain.agreement.repository.api.AgreementRepository
import idf.kz.core.banking.domain.agreement.service.api.AgreementService
import idf.kz.library.mapper.Mapper
import idf.kz.library.service.AbstractService
import idf.kz.library.service.Service
import reactor.core.publisher.Flux


class DefaultAgreementService(
  private val agreementRepository: AgreementRepository,
  private val agreementMapper: DefaultAgreementMapper
) : AbstractService<AgreementDto, Agreement, AgreementRepository, Mapper<Agreement, AgreementDto>>(
  agreementRepository,
  agreementMapper
), Service<AgreementDto>, AgreementService {
  override fun findByStatus(status: AgreementStatus): Flux<Agreement> =  agreementRepository.findByStatus(status)
}