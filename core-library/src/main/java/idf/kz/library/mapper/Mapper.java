package idf.kz.library.mapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Mapper<E, D> {

  default Mono<D> toDtoMono(Mono<E> entity) {
    return entity.map(this::toDto);
  }

  default Flux<D> toDtoFLux(Flux<E> entity) {
    return entity.map(this::toDto);
  }

  D toDto(E entity);

  E toEntity(D dto);
}
