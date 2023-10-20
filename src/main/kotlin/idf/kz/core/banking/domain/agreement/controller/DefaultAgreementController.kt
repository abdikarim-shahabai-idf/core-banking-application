package idf.kz.core.banking.domain.agreement.controller

import idf.kz.bank.model.Agreement
import idf.kz.bank.model.dto.AgreementDto
import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.core.banking.domain.agreement.service.api.AgreementService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("agreement")
class DefaultAgreementController(
  var agreementService: AgreementService
) {

  @GetMapping
  fun findAll(): Flux<AgreementDto> = agreementService.findAll()

  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): Mono<AgreementDto> = agreementService.findById(id)

  @PostMapping
  fun create(@RequestBody agreementDto: AgreementDto): Mono<AgreementDto> = agreementService.persist(agreementDto)

  @PutMapping("/{id}")
  fun update(@RequestBody agreementDto: AgreementDto, @PathVariable id: Long): Mono<AgreementDto> =
    agreementService.update(agreementDto, id)

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long): Mono<Void> = agreementService.deleteById(id)


  @GetMapping("/status/{status}")
  fun findAllByAgreementStatus(@PathVariable status: AgreementStatus): Flux<Agreement> =
    agreementService.findByStatus(status)
}