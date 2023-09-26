package idf.kz.core.banking.config

import idf.kz.core.banking.api.handler.AgreementHandler
import idf.kz.library.handler.Handler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RouterConfig {
  private val ACCEPT_JSON = RequestPredicates.accept(MediaType.APPLICATION_JSON)

  private val PATH_VARIABLE_ID = "{id:[0-9]+}"

  @Bean
  fun creditRoute(handler: AgreementHandler): RouterFunction<ServerResponse?>? {
    return RouterFunctions
      .route()
      .path(
        "/credit"
      ) { builder: RouterFunctions.Builder ->
        builder
          .GET(PATH_VARIABLE_ID, handler::findAllById)
          .GET(HandlerFunction<ServerResponse> { serverRequest: ServerRequest? -> handler.findAll() })
          .PUT(
            PATH_VARIABLE_ID,
            RequestPredicates.accept(MediaType.APPLICATION_JSON),
            handler::update
          )
          .POST(ACCEPT_JSON, handler::create)
      }
      .build()
  }

  private fun createRouterFunctionByPath(handler: Handler, path: String): RouterFunction<ServerResponse?>? {
    return RouterFunctions.route()
      .path(
        path
      ) { builder: RouterFunctions.Builder ->
        builder
          .GET(PATH_VARIABLE_ID, handler::findAllById)
          .GET(HandlerFunction<ServerResponse> { serverRequest: ServerRequest? -> handler.findAll() })
          .POST(ACCEPT_JSON, handler::create)
      }
      .build()
  }

  private fun getRequestPredicate(): RequestPredicate {
    return RequestPredicates.all()
  }
}