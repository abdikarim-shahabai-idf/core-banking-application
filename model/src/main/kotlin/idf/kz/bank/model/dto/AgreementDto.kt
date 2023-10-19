package idf.kz.bank.model.dto

import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.bank.model.enums.FinanceType
import lombok.Data
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Data
data class AgreementDto(
  var status: AgreementStatus,
  var customerId: Long,
  var dateRequested: LocalDateTime,
  var startDate: LocalDate,
  var endDate: LocalDate,
  var financeType: FinanceType,
  var amount: BigDecimal,
  var interestRate: BigDecimal
)
