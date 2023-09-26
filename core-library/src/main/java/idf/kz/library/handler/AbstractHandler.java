package idf.kz.library.handler;

import static idf.kz.library.exception.message.MessageResponse.INCORRECT_FIELDS;

import idf.kz.library.exception.CoreException;
import idf.kz.library.service.Service;
import idf.kz.library.util.ReflectionUtil;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerResponse.BodyBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * It is an abstract class for web layer for interaction with view, that implements the Handler interface and contains
 * all the logic for that duplicated from child classes.
 *
 * @param <D> DTO from Jooq Pojo
 * @param <S> Class that implementations interface Service from module core contains primary logic @see
 * {@link idf.kz.library.service.AbstractService}
 */
public abstract class AbstractHandler<
    D,
    S extends Service<D>
    >
    implements Handler {

  protected final S service;
  private final Class<D> dtoType;

  protected AbstractHandler(S service) {
    this.service = service;
    this.dtoType = getClassFromFirstType();
  }

  @Override
  public Mono<ServerResponse> create(ServerRequest request) {
    Mono<D> monoDto = request.bodyToMono(dtoType);
    return monoDto
        .flatMap(service::persist)
        .flatMap(dto ->
            getOkResponseBuilder()
                .bodyValue(dto)
        )
        .switchIfEmpty(
            getBadRequestResponseBuilder()
                .bodyValue(INCORRECT_FIELDS.getDescription())
        )
        .onErrorResume(exception ->
            getBadRequestResponseBuilder()
                .bodyValue(exception.getMessage())
        );
  }

  @Override
  public Mono<ServerResponse> findAll() {
    return ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findAll(), dtoType)
        .onErrorResume(exception ->
            Mono.just(exception.getMessage())
                .flatMap(dto ->
                    ServerResponse
                        .status(500)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(exception.getMessage())
                )
        );
  }

  @Override
  public Mono<ServerResponse> findAllById(ServerRequest request) {
    return createMonoServerResponseFromMono(
        getIdFromRequest(request).flatMap(service::findById));
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    Mono<D> dtoMono = request.bodyToMono(dtoType);
    final long id = Long.parseLong(request.pathVariable("id"));
    return dtoMono
        .flatMap(dto -> service.update(dto, id))
        .flatMap(dto -> getOkResponseBuilder().bodyValue(dto))
        .switchIfEmpty(
            getBadRequestResponseBuilder()
                .bodyValue(INCORRECT_FIELDS.getDescription())
        )
        .onErrorResume(exception ->
            getBadRequestResponseBuilder()
                .bodyValue(exception.getMessage())
        );
  }

  /**
   * @param <P> is type parameter for argument Mono<P> mono
   * @return Mono<ServerResponse>
   */
  protected <P> Mono<ServerResponse> createMonoServerResponseFromMono(Mono<P> mono) {
    return mono.flatMap(dto ->
            getOkResponseBuilder()
                .bodyValue(dto))
        .onErrorResume(CoreException.class, exception ->
            getBadRequestResponseBuilder()
                .bodyValue(exception.getMessage()));
  }

  /**
   * @param <P> is type parameter for argument Flux<P> flux
   * @return Mono<ServerResponse>
   */
  protected <P> Mono<ServerResponse> createMonoServerResponseFromFlux(Flux<P> flux) {
    return Mono
        .from(flux)
        .flatMap(dto ->
            getOkResponseBuilder().bodyValue(dto))
        .onErrorResume(CoreException.class, exception ->
            getBadRequestResponseBuilder().bodyValue(exception.getMessage()));
  }

  protected BodyBuilder getBadRequestResponseBuilder() {
    return ServerResponse
        .badRequest()
        .contentType(MediaType.APPLICATION_JSON);
  }

  protected BodyBuilder getOkResponseBuilder() {
    return ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON);
  }

  protected String getParamFromRequest(String paramName, ServerRequest request) {
    return request
        .queryParam(paramName)
        .orElseThrow(() -> CoreException.queryParamRequired(paramName));
  }

  private Mono<Long> getIdFromRequest(ServerRequest request) {
    return Mono.just(Long.parseLong(request.pathVariable("id")));
  }

  @SuppressWarnings("unchecked")
  private Class<D> getClassFromFirstType() {
    return (Class<D>) ReflectionUtil.getClassByTypeParameterPosition(getClass(), 0);
  }
}
