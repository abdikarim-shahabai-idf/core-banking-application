package idf.kz.library.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface Handler {

  Mono<ServerResponse> create(ServerRequest request);

  Mono<ServerResponse> findAll();

  Mono<ServerResponse> findAllById(ServerRequest request);

  Mono<ServerResponse> update(ServerRequest request);
}
