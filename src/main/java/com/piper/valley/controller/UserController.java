package com.piper.valley.controller;

import com.piper.valley.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User>users;

    public UserController()
    {
        users= new ArrayList<>();
        users.add(new User("Refaie","ref","123","ref@go.com"));
        users.add(new User("Hamed","hamed","1234","hamed@go.com"));
        users.add(new User("Sherif","sherif","12345","sherif@go.com"));

    }
    @RequestMapping("/all" )
    public List<User> getall()
    {
        return users;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public List<User>register(@RequestBody User user)
    {
        users.add(user);
        return users;
    }

}
