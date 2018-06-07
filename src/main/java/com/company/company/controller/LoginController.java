package com.company.company.controller;

import com.company.company.NotNullByDefault;
import com.company.company.config.security.TokenProvider;
import com.company.company.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/login", method = POST, produces = MediaType.TEXT_PLAIN)
    public @ResponseBody String getToken(@RequestBody User user) {

        String username = user.getUsername();
        String password = user.getPassword();

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
