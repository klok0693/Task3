package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.JpaEntity;
import com.company.company.model.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@RestController
@RequestMapping(produces = "application/json", value = "/company")
public abstract class GenericRestController<E extends JpaEntity> {
    private GenericEntityService<E> service;

    @Autowired
    public GenericRestController(GenericEntityService<E> service) {
        this.service = service;
    }


    @RequestMapping(method = GET)
    public List<E> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = GET, value = "/findById")
    public E findById(Integer integer) {
        return service.findById(integer).get();
    }

    @RequestMapping(method = POST)
    public E save(E s) {
        return service.save(s);
    }

    @RequestMapping(method = PUT)
    public E update(E s) {
        return service.save(s);
    }

    @RequestMapping(method = DELETE)
    public void delete(E e) {
        service.delete(e);
    }

    public void deleteInBatch(Iterable<E> iterable) {
        service.deleteInBatch(iterable);
    }

    public List<E> findAllById(Iterable<Integer> iterable) {
        return service.findAllById(iterable);
    }

    public boolean exists(Example<E> example) {
        return service.exists(example);
    }

    public boolean existsById(Integer integer) {
        return service.existsById(integer);
    }

    public void deleteAll(Iterable<? extends E> iterable) {
        service.deleteAll(iterable);
    }

    public void deleteAll() {
        service.deleteAll();
    }

    public long count(Example<E> example) {
        return service.count(example);
    }

    public Page<E> findAll(Example<E> example, Pageable pageable) {
        return service.findAll(example, pageable);
    }

    public Optional<E> findOne(Example<E> example) {
        return service.findOne(example);
    }

    public E saveAndFlush(E s) {
        return service.saveAndFlush(s);
    }

    public long count() {
        return service.count();
    }

    public Page<E> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    public void deleteAllInBatch() {
        service.deleteAllInBatch();
    }

    public List<E> findAll(Example<E> example) {
        return service.findAll(example);
    }

    public List<E> saveAll(Iterable<E> iterable) {
        return service.saveAll(iterable);
    }

    public E getOne(Integer integer) {
        return service.getOne(integer);
    }

    public List<E> findAll(Example<E> example, Sort sort) {
        return service.findAll(example, sort);
    }

    public void flush() {
        service.flush();
    }

    public List<E> findAll(Sort sort) {
        return service.findAll(sort);
    }

    public void deleteById(Integer integer) {
        service.deleteById(integer);
    }
}
