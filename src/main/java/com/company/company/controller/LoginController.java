package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.config.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@NotNullByDefault

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;

    private TokenProvider tokenProvider;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public String getToken(@PathVariable("username") String username,@PathVariable("password") String password) {

        String password1 = new BCryptPasswordEncoder().encode(password);

        System.out.println("\n");
        System.out.println("password before = " + password);
        System.out.println("password after = "+ password1);
        System.out.println("\n");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authToken);
        return tokenProvider.createToken(username, password);

    }
}
