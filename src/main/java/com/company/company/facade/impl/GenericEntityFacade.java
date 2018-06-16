package com.company.company.facade.impl;

import com.company.company.facade.EntityFacade;
import com.company.company.model.entity.JpaEntity;
import com.company.company.service.dto.JasperCompiler;
import com.company.company.service.entity.EntityService;
import com.company.company.util.NotNullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NotNullByDefault

@Component
//@AllArgsConstructor(access = PROTECTED)
public abstract class GenericEntityFacade<E extends JpaEntity> implements EntityFacade<E> {
    protected EntityService<E> service;

    @Autowired
    private JasperCompiler compiler;

    protected GenericEntityFacade(EntityService<E> service) {
        this.service = service;
    }

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
        /*try {
            compiler.createPDFReport("/templates/report.jrxml", "employeeReport.csv");
        }
        catch (JRException | SQLException e) {
            e.printStackTrace();
        }*/
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
