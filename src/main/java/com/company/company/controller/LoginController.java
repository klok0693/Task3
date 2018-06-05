package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.config.security.TokenProvider;
import com.company.company.entity.auth.User;
import com.company.company.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@NotNullByDefault

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    private UserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String getToken(@RequestBody User user, HttpServletResponse response) {


        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword());

        System.out.println("\n");
        System.out.println("auth = "+ authToken);
        System.out.println("\n");

        authenticationManager.authenticate(authToken);
        return tokenProvider.createToken(user.getUsername());

    }
}
