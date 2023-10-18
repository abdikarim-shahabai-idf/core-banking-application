package idf.kz.core.banking.domain.agreement.mapper

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
  unmappedTargetPolicy = ReportingPolicy.IGNORE,
  componentModel = MappingConstants.ComponentModel.SPRING,
  implementationName = "Default<CLASS_NAME>",
)
interface AgreementMapper: idf.kz.library.mapper.Mapper<Agreement, AgreementDto>