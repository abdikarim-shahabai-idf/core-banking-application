package idf.kz.core.banking.domain.agreement.service.impl

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.core.banking.domain.agreement.mapper.DefaultAgreementMapper
import idf.kz.core.banking.domain.agreement.repository.api.AgreementRepository
import idf.kz.core.banking.domain.agreement.service.api.AgreementService
import idf.kz.library.mapper.Mapper
import idf.kz.library.service.AbstractService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class DefaultAgreementService(
  private val agreementRepository: AgreementRepository,
  agreementMapper: DefaultAgreementMapper
) : AgreementService,
  AbstractService<AgreementDto, Agreement, AgreementRepository, Mapper<Agreement, AgreementDto>>
    (
    agreementRepository,
    agreementMapper
  ) {
  override fun findByStatus(status: AgreementStatus): Flux<Agreement> = agreementRepository.findByStatus(status)
  override fun update(dto: AgreementDto, id: Long): Mono<AgreementDto> {
    val agreement = mapper.toEntity(dto)
    agreement.id = id
    return mapper.toDtoMono(agreementRepository.save(agreement))
  }
}
