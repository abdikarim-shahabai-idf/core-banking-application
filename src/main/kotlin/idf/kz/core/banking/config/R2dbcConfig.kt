package idf.kz.core.banking.config

import idf.kz.bank.model.enums.AgreementStatus
import idf.kz.bank.model.enums.FinanceType
import idf.kz.core.banking.domain.agreement.mapper.AgreementStatusConverter
import idf.kz.core.banking.domain.agreement.mapper.FinanceTypeConverter
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.codec.EnumCodec
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class R2dbcConfig(
  @Value("\${spring.r2dbc.username}")
  private val username: String,
  @Value("\${spring.r2dbc.password}")
  private val password: String
) : AbstractR2dbcConfiguration() {

  override fun getCustomConverters(): List<Any?> {
    return listOf(
      AgreementStatusConverter(),
      FinanceTypeConverter()
    )
  }

  @Bean
  override fun connectionFactory(): ConnectionFactory {
    return PostgresqlConnectionFactory(
      PostgresqlConnectionConfiguration.builder()
        .host("localhost")
        .port(5432)
        .database("core_banking")
        .username(username)
        .password(password)
        .schema("accounting")
        .codecRegistrar(EnumCodec.builder().withEnum("agreement_status", AgreementStatus::class.java).build())
        .codecRegistrar(EnumCodec.builder().withEnum("finance_type_enum", FinanceType::class.java).build())
        .build()
    )
  }
}
