package com.takeo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.security.Key;
@Component
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public boolean validateToken(String token) throws AuthenticationException {
    try{    Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token);
        return true;
    }catch (Exception e){
        throw new AuthenticationException(e.getMessage());
    }
    }

}
