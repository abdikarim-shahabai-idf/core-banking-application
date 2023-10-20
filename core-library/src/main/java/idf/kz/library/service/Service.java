package idf.kz.library.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * Core service interface
 *
 * @param <D> Dto from Entity
 */
public interface Service<D> {

  Mono<D> persist(D dto);
  Mono<Void> deleteById(Long id);
  Mono<D> findById(long id);
  Flux<D> findAll();
  Mono<D> update(D dto, long id);
}
