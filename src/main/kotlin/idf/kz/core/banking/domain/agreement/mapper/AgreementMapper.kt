package idf.kz.core.banking.domain.agreement.mapper

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import org.mapstruct.Mapper

@Mapper
interface AgreementMapper: idf.kz.library.mapper.Mapper<Agreement,AgreementDto>