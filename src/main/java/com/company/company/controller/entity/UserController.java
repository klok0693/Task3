package com.company.company.controller.entity;

import com.company.company.facade.UserFacade;
import com.company.company.model.entity.auth.User;
import com.company.company.service.entity.impl.UserServiceImpl;
import com.company.company.util.NotNullByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@NotNullByDefault

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = "/users")
public class UserController extends GenericRestController<User> {

    @Autowired
    public UserController(UserFacade service) {
        super(service);
    }


    @PreAuthorize("hasRole({'ROLE_USER'}) or hasRole('ROLE_ADMIN')")
    @RequestMapping(method = DELETE, value = "current")
    public boolean delete(){

        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println(username);

            ((UserServiceImpl) service).deleteByUsername(username);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
