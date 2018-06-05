package com.company.company.config.security;

import com.company.company.NotNullByDefault;
import com.company.company.model.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@NotNullByDefault

@Component
public class TokenProvider {

    private static final String SECRET = "secret";

    private static final int EXPIRATION_TIME = 60*60*6;

    @Autowired
    UserService userService;

    public String createToken(String username) {
        UserDetails user = userService.loadUserByUsername(username);

        Claims claims = Jwts.claims().setSubject(username);

        ArrayList<String> rolesList = new ArrayList<String>();

        for (GrantedAuthority role : user.getAuthorities()) {
            rolesList.add(role.getAuthority());
        }
        claims.put("roles", rolesList);

        String token = Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        return token;
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
