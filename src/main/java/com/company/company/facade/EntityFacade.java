package com.company.company.facade;

import com.company.company.model.entity.JpaEntity;
import com.company.company.util.NotNullByDefault;

import java.util.Optional;
@NotNullByDefault
public interface EntityFacade<E extends JpaEntity> {

    //SAVE-UPDATE
    E save(E s) throws Exception;
    Iterable<E> saveAll(Iterable<E> iterable);

    //DELETE
    void delete(E e);
    void deleteAll();
    void deleteById(Integer integer);
    void deleteAll(Iterable<? extends E> iterable);

    //READ
    Iterable<E> findAll();
    Optional<E> findById(Integer integer);
    Iterable<E> findAllById(Iterable<Integer> iterable);

    //ELSE
    long count();
    boolean existsById(Integer integer);
}
