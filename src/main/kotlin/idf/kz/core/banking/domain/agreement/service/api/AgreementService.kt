package idf.kz.core.banking.domain.agreement.service.api

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.library.service.Service
import reactor.core.publisher.Flux

interface AgreementService: Service<AgreementDto> {
  fun findByStatus(status: AgreementStatus): Flux<Agreement>
}