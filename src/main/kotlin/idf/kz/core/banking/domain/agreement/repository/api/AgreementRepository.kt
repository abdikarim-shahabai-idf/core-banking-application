package idf.kz.core.banking.domain.agreement.repository.api

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.enums.AgreementStatus
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface AgreementRepository : ReactiveCrudRepository<Agreement, Long> {
  fun findByStatus(status: AgreementStatus): Flux<Agreement>
}