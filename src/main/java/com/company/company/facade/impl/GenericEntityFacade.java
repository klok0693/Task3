package com.company.company.facade.impl;

import com.company.company.facade.EntityFacade;
import com.company.company.model.entity.JpaEntity;
import com.company.company.service.entity.EntityService;
import com.company.company.util.NotNullByDefault;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@NotNullByDefault

@Component
@AllArgsConstructor(access = PROTECTED)
public abstract class GenericEntityFacade<E extends JpaEntity> implements EntityFacade<E> {
    protected EntityService<E> service;

    //SAVE-UPDATE
    public E save(E s) throws Exception {
        return service.save(s);
    }

    public Iterable<E> saveAll(Iterable<E> iterable) {
        return service.saveAll(iterable);
    }


    //DELETE
    public void delete(E e) {
        service.delete(e);
    }

    public void deleteAll() {
        service.deleteAll();
    }

    public void deleteById(Integer integer) {
        service.deleteById(integer);
    }

    public void deleteAll(Iterable<? extends E> iterable) {
        service.deleteAll(iterable);
    }


    //READ
    public Iterable<E> findAll() {
        return service.findAll();
    }

    public Optional<E> findById(Integer integer) {
        return service.findById(integer);
    }

    public Iterable<E> findAllById(Iterable<Integer> iterable) {
        return service.findAllById(iterable);
    }


    //ELSE
    public boolean existsById(Integer integer) {
        return service.existsById(integer);
    }

    public long count() {
        return service.count();
    }
}
