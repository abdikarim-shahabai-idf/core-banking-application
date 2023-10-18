package idf.kz.core.banking.domain.agreement.service.api

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.enums.AgreementStatus
import reactor.core.publisher.Flux

interface AgreementService{
  fun findByStatus(status: AgreementStatus): Flux<Agreement>
}