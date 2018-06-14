package com.company.company.controller.entity;

import com.company.company.facade.EntityFacade;
import com.company.company.model.entity.JpaEntity;
import com.company.company.util.NotNullByDefault;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lombok.AccessLevel.PROTECTED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@AllArgsConstructor(access = PROTECTED)

@RestController
@RequestMapping(produces = "application/json")
public abstract class GenericRestController<E extends JpaEntity> {
    protected EntityFacade<E> service;


    @RequestMapping(method = GET)
    public @ResponseBody Iterable<E> findAll() {
        return service.findAll();
    }


    @RequestMapping(method = GET, value = "/id")
    public @ResponseBody E findById(@RequestParam("id") @Valid Integer id) {
        return service.findById(id).get();
    }


    @RequestMapping(method = POST)
    public @ResponseBody E save(@RequestBody @Valid E entity) throws Exception {
        return service.save(entity);
    }


    @RequestMapping(method = PUT)
    public @ResponseBody E update(@RequestBody @Valid E entity) throws Exception {
        return service.save(entity);
    }


    @RequestMapping(method = DELETE)
    public void delete(@RequestBody @Valid E entity) {
        service.delete(entity);
    }


    @RequestMapping(method = DELETE, value = "/{id}")
    public void deleteById(@PathVariable(value = "id") @Valid Integer id) {
        service.deleteById(id);
    }
}
