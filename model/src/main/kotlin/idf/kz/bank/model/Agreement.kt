package idf.kz.bank.model

import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.bank.model.enums.FinanceType
import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Data
@Table("agreement")
data class Agreement (
   @Id
   var id: Long?,
   var status: AgreementStatus,
   var customerId: Long,
   var dateRequested: LocalDateTime,
   var startDate: LocalDate,
   var endDate: LocalDate,
   var financeType: FinanceType,
   var amount: BigDecimal,
   var interestRate: BigDecimal
)
