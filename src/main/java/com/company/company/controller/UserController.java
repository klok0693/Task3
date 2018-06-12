package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.entity.auth.User;
import com.company.company.model.service.UserEmailService;
import com.company.company.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@NotNullByDefault

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;
    private UserEmailService emailService;

    @Autowired
    public UserController(UserService userService, UserEmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(method = GET)
    public List listUser(){
        return userService.findAll();
    }

    @RequestMapping(method = POST)
    public User create(@RequestBody User user) throws Exception {

        userService.save(user);
        emailService.sendMessageToAdmins("usrmbtask@gmail.com", user.getUsername());

        return user;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = DELETE)
    public String delete(@PathVariable(value = "id") Integer id){
        userService.delete(id);
        return "success";
    }


    @PreAuthorize("hasRole({'ROLE_USER'}) or hasRole('ROLE_ADMIN')")
    @RequestMapping(method = DELETE)
    public boolean delete(){

        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println(username);

            userService.deleteByUsername(username);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
