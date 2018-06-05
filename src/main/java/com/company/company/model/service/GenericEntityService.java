package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.JpaEntity;
import com.company.company.model.data.GenericEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Service
@AllArgsConstructor
@Transactional(isolation = REPEATABLE_READ)
public abstract class GenericEntityService<E extends JpaEntity> {
    private GenericEntityRepository<E> repository;

    public E save(E s) {
        return repository.save(s);
    }

    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    public Iterable<E> saveAll(Iterable<E> iterable) {
        return repository.saveAll(iterable);
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public void deleteAll(Iterable<? extends E> iterable) {
        repository.deleteAll(iterable);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Iterable<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(Integer integer) {
        return repository.findById(integer);
    }

    public long count() {
        return repository.count();
    }

    public Iterable<E> findAllById(Iterable<Integer> iterable) {
        return repository.findAllById(iterable);
    }

    public void delete(E e) {
        repository.delete(e);
    }
}
