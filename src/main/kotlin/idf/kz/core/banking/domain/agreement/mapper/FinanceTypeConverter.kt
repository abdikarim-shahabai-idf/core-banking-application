package idf.kz.core.banking.domain.agreement.mapper

import idf.kz.bank.model.enums.FinanceType
import org.springframework.data.convert.WritingConverter
import org.springframework.data.r2dbc.convert.EnumWriteSupport

@WritingConverter
class FinanceTypeConverter: EnumWriteSupport< FinanceType> ()

