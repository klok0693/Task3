package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.JpaEntity;
import com.company.company.model.service.GenericEntityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by klok on 28.5.18.
 */
@NotNullByDefault

@RestController
@AllArgsConstructor
@RequestMapping(produces = "application/json")
public abstract class GenericRestController<E extends JpaEntity> {
    private GenericEntityService<E> service;


    @RequestMapping(method = GET)
    public @ResponseBody Iterable<E> findAll() {
        return service.findAll();
    }


    @RequestMapping(method = GET, value = "/id")
    public @ResponseBody E findById(@RequestParam("id") @Valid Integer id) {
        return service.findById(id).get();
    }


    @RequestMapping(method = POST)
    public @ResponseBody E save(@RequestBody @Valid E entity) {
        return service.save(entity);
    }



    @RequestMapping(method = PUT)
    public @ResponseBody E update(@RequestBody @Valid E entity) {
        return service.save(entity);
    }


    @RequestMapping(method = DELETE)
    public void delete(@RequestBody @Valid E entity) {
        service.delete(entity);
    }


    @RequestMapping(method = DELETE, value = "/id")
    public void deleteById(@RequestParam("id") @Valid Integer id) {
        service.deleteById(id);
    }
}
