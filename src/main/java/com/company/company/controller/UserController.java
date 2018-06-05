package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.User;
import com.company.company.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@NotNullByDefault

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public List listUser(){
        return userService.findAll();
    }

    @RequestMapping(method = POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public String delete(@PathVariable(value = "id") Integer id){
        userService.delete(id);
        return "success";
    }
}
