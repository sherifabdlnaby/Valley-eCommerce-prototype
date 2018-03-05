package com.helloworld.controller;
import com.helloworld.entity.Entity;
import com.helloworld.entity.User;
import com.helloworld.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/*
* DA HTML NORMAL CONTROLLER EXAMPLE
* */
@Controller
@RequestMapping("/entities")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllEntities(Model model) {
        Collection<User> entities = entityService.getAllEntities();
        model.addAttribute("entities"  , entities );
        return "entities/index";
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEntityById(@PathVariable("id") int id, Model modelMap) {

        User user = entityService.getEntityById(id);

        //Pass to View
        modelMap.addAttribute("id"  , user.getId()  );
        modelMap.addAttribute("name", entity.getName());
        modelMap.addAttribute("desc", entity.getDesc());

        return "entities/view";
    }*/
}
