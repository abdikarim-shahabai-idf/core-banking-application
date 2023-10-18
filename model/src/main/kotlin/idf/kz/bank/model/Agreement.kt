package idf.kz.bank.model

import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.bank.model.enums.FinanceTypeEnum
import java.math.BigDecimal
import java.time.LocalDate

data class Agreement (
   val id: Long,
   val status: AgreementStatus,
   val customerId: Long,
   val dateRequested: LocalDate,
   val startDate: LocalDate,
   val endDate: LocalDate,
   val financeTypeEnum: FinanceTypeEnum,
   val amount: BigDecimal
)