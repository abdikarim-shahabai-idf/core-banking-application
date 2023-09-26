package idf.kz.core.banking.api.handler

import idf.kz.bank.model.dto.AgreementDto
import idf.kz.core.banking.domain.agreement.service.impl.DefaultAgreementService
import idf.kz.library.handler.AbstractHandler
import org.springframework.stereotype.Component

@Component
class AgreementHandler(service: DefaultAgreementService?) : AbstractHandler<AgreementDto,DefaultAgreementService>(
  service
)