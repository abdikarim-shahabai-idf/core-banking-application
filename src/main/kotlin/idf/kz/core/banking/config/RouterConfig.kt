package idf.kz.core.banking.config

import idf.kz.core.banking.api.handler.AgreementHandler
import idf.kz.library.handler.Handler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RouterConfig {
  companion object {
    private val ACCEPT_JSON = RequestPredicates.accept(MediaType.APPLICATION_JSON)
    private const val AGREEMENT_PATH = "/agreement"
    private const val PATH_VARIABLE_ID = "{id:[0-9]+}"
  }

  @Bean
  fun agreementRouteFunction(handler: AgreementHandler) =
    crudRouteFunctions(handler, AGREEMENT_PATH)

  fun crudRouteFunctions(handler: Handler, path: String): RouterFunction<ServerResponse> {
    return RouterFunctions
      .route()
      .path(path) { builder: RouterFunctions.Builder ->
        builder
          .GET(PATH_VARIABLE_ID, handler::findAllById)
          .GET { handler.findAll() }
          .PUT(
            PATH_VARIABLE_ID,
            RequestPredicates.accept(MediaType.APPLICATION_JSON),
            handler::update
          )
          .POST(ACCEPT_JSON, handler::create)
          .DELETE(PATH_VARIABLE_ID, handler::deleteById)
      }.build()
  }
}
