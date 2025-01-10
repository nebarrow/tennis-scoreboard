package com.nebarrow.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<K, E> {

    E save(E entity);

    E update(E entity);

    void delete(K id);

    Optional<E> getById(K id);

    List<E> getAll();
}
