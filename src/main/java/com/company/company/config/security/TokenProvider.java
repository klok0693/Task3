package com.company.company.config.security;

import com.company.company.NotNullByDefault;
import com.company.company.model.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

@NotNullByDefault

@Configuration
public class TokenProvider {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    SecretKey key = MacProvider.generateKey();
    byte[] keyBytes = key.getEncoded();
    String base64Encoded = TextCodec.BASE64URL.encode(keyBytes);

    @Autowired
    public TokenProvider(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public String createToken(String username, String password) {

        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder().setClaims(claims)
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
