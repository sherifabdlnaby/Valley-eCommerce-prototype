/*
package com.piper.valley.controller;

import com.piper.valley.entity.Entity;
import com.piper.valley.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

*/
/*
* DA REST CONTROLLER EXAMPLE
* *//*

@RequestMapping("/RESTentities")
@RestController
public class RestEntityController {

    @Autowired
    private EntityService entityService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Entity> getAllEntities() {
        return entityService.getAllEntities();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Entity getEntityById(@PathVariable("id") int id) {
        return entityService.getEntityById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEntityById(@PathVariable("id") int id) {
        entityService.removeEntityById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntityById(@RequestBody Entity entity) {
        entityService.updateEntity(entity);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertEntity(@RequestBody Entity entity) {
        entityService.insertEntity(entity);
    }
}
*/
