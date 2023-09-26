package idf.kz.core.banking.config

import idf.kz.core.banking.domain.agreement.mapper.AgreementMapper
import idf.kz.core.banking.domain.agreement.repository.api.AgreementRepository
import idf.kz.core.banking.domain.agreement.service.impl.DefaultAgreementService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ComponentServiceConfig {
  @Bean
  fun agreementService(agreementRepository: AgreementRepository, agreementMapper: AgreementMapper)
  = DefaultAgreementService(agreementRepository, agreementMapper)
}