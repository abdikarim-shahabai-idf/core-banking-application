package idf.kz.library.service;

import idf.kz.library.mapper.Mapper;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * It is an abstract class service, layer between data and web, that implements the Service interface, contains base
 * logic for all domains and the code that duplicated.
 *
 * @param <D> DTO from DB Entity
 * @param <E> DB Entity - Jooq Pojo
 * @param <R> is a repository data layer @see {@link ReactiveCrudRepository}
 * @param <M> is a mapper for converting objects from dto to entity and vice vera @see {@link Mapper}
 */
public abstract class AbstractService<
    D,
    E,
    R extends ReactiveCrudRepository<E,Long>,
    M extends Mapper<E, D>
    > implements Service<D> {

  protected final R repository;
  protected final M mapper;

  protected AbstractService(R repository, M mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Mono<D> persist(D dto) {
    return mapper.toDtoMono(
        repository.save(mapper.toEntity(dto))
    );
  }

  @Override
  public Mono<Void> deleteById(long id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<D> findById(long id) {
    return mapper.toDtoMono(repository.findById(id));
  }

  @Override
  public Flux<D> findAll() {
    return mapper.toDtoFLux(repository.findAll());
  }

  @Override
  public abstract Mono<D> update(D dto, long id);
}
