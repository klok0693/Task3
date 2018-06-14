package com.company.company.service.entity.impl;

import com.company.company.model.entity.JpaEntity;
import com.company.company.repository.GenericEntityRepository;
import com.company.company.service.entity.EntityService;
import com.company.company.util.NotNullByDefault;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@AllArgsConstructor(access = PROTECTED)

@Service
@Transactional(isolation = REPEATABLE_READ)
public abstract class GenericEntityService<E extends JpaEntity> implements EntityService<E> {
    protected GenericEntityRepository<E> repository;


    //SAVE-UPDATE
    public E save(E s) {
        return repository.save(s);
    }

    public Iterable<E> saveAll(Iterable<E> iterable) {
        return repository.saveAll(iterable);
    }


    //DELETE
    public void delete(E e) {
        repository.delete(e);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public void deleteAll(Iterable<? extends E> iterable) {
        repository.deleteAll(iterable);
    }


    //READ
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(Integer integer) {
        return repository.findById(integer);
    }

    public Iterable<E> findAllById(Iterable<Integer> iterable) {
        return repository.findAllById(iterable);
    }


    //ELSE
    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    public long count() {
        return repository.count();
    }
}
