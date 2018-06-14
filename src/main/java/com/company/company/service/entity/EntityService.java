package com.company.company.service.entity;

import com.company.company.model.entity.JpaEntity;
import com.company.company.util.NotNullByDefault;

import java.util.Optional;
@NotNullByDefault
public interface EntityService<E extends JpaEntity> {

    //SAVE-UPDATE
    E save(E s);
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
