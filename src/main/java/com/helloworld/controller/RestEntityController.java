package com.helloworld.controller;

import com.helloworld.entity.Entity;
import com.helloworld.entity.User;
import com.helloworld.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/*
* DA REST CONTROLLER EXAMPLE
* */
@RequestMapping("/RESTentities")
@RestController
public class RestEntityController {

    @Autowired
    private EntityService entityService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllEntities() {
        return entityService.getAllEntities();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getEntityById(@PathVariable("id") int id) {
        return entityService.getEntityById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEntityById(@PathVariable("id") int id) {
        entityService.removeEntityById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntityById(@RequestBody User entity) {
        entityService.updateEntity(entity);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertEntity(@RequestBody User entity) {
        entityService.insertEntity(entity);
    }
}
