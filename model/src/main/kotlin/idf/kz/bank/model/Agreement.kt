package idf.kz.bank.model

import idf.kz.bank.model.enum.AgreementStatus
import idf.kz.bank.model.enum.FinanceTypeEnum
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDate

data class Agreement (
  @Id
  private val id: Long,
  private val status: AgreementStatus,
  private val customerId: Long,
  private val dateRequested: LocalDate,
  private val startDate: LocalDate,
  private val endDate: LocalDate,
  private val financeTypeEnum: FinanceTypeEnum,
  private val amount: BigDecimal
)