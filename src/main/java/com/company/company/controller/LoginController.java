package com.company.company.controller;

import com.company.company.util.NotNullByDefault;
import com.company.company.config.security.jwt.TokenProvider;
import com.company.company.model.entity.auth.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@NotNullByDefault

@Log4j
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
        log.debug("\n password before = "+password+"\n"+"password after = "+password1+"\n");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(authToken);
        return tokenProvider.createToken(username);
    }


    @RequestMapping(value = "/authority", method = GET)
    public Collection<? extends GrantedAuthority> getAuthority() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }
}
