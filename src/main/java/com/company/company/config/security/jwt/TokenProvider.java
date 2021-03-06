package com.company.company.config.security.jwt;

import com.company.company.service.entity.impl.UserServiceImpl;
import com.company.company.util.NotNullByDefault;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@NotNullByDefault

@Configuration
public class TokenProvider {

    private UserServiceImpl userService;

    String base64Encoded = TextCodec.BASE64URL.encode(MacProvider.generateKey().getEncoded());

    @Autowired
    public TokenProvider(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*120))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, base64Encoded)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(base64Encoded).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = this.userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
