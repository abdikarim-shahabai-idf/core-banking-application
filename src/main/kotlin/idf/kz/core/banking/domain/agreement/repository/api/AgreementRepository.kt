package idf.kz.core.banking.domain.agreement.repository.api

import idf.kz.bank.model.Agreement
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface AgreementRepository : ReactiveCrudRepository<Agreement, Long> {
  fun findByName(name: String): Flux<Agreement>
}