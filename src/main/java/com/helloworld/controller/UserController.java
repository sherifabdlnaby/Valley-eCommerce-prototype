package com.helloworld.controller;

import com.helloworld.dao.FakeEntityDaoImpl;
import com.helloworld.entity.User;
import com.helloworld.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private EntityService entityService;
    public UserController()
    {


    }



    /*@RequestMapping("/all" )
    public List<User> getall()
    {
        return FakeEntityDaoImpl.users;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public List<User>register(@RequestBody User user)
    {
        users.add(user);
        return users;
    }*/

}
