package com.company.company.model.service;

import com.company.company.NotNullByDefault;
import com.company.company.entity.JpaEntity;
import com.company.company.model.data.GenericEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@Service
public abstract class GenericEntityService<E extends JpaEntity> {
    private GenericEntityRepository<E> repository;

    @Autowired
    public GenericEntityService(GenericEntityRepository<E> repository) {
        this.repository = repository;
    }


    public List<E> findAll() {
        return repository.findAll();
    }

    public E saveAndFlush(E s) {
        return repository.saveAndFlush(s);
    }

    public void deleteAll(Iterable<? extends E> iterable) {
        repository.deleteAll(iterable);
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public void delete(E e) {
        repository.delete(e);
    }

    public boolean exists(Example<E> example) {
        return repository.exists(example);
    }

    public E getOne(Integer integer) {
        return repository.getOne(integer);
    }

    public long count() {
        return repository.count();
    }

    public void deleteInBatch(Iterable<E> iterable) {
        repository.deleteInBatch(iterable);
    }

    public boolean existsById(Integer integer) {
        return repository.existsById(integer);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Optional<E> findOne(Example<E> example) {
        return repository.findOne(example);
    }

    public E save(E s) {
        return repository.save(s);
    }

    public Page<E> findAll(Example<E> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    public List<E> findAll(Example<E> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    public void flush() {
        repository.flush();
    }

    public Optional<E> findById(Integer integer) {
        return repository.findById(integer);
    }

    public long count(Example<E> example) {
        return repository.count(example);
    }

    public List<E> findAllById(Iterable<Integer> iterable) {
        return repository.findAllById(iterable);
    }

    public List<E> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public List<E> findAll(Example<E> example) {
        return repository.findAll(example);
    }

    public List<E> saveAll(Iterable<E> iterable) {
        return repository.saveAll(iterable);
    }
}
