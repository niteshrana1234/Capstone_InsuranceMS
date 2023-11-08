package com.takeo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtGeneratorValidator {
    private static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//   private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, authentication);
    }
    public String createToken(Map<String,Object> claims,Authentication authentication) {
        String role =authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        String userName = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);

      String token =  Jwts.builder()
                .setSubject(userName)
                .claim("role",role)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }

//    public Claims extractAllClaims(String token) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
//        } catch (JwtException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately based on your use case.
//            return null; // Or throw a custom exception if required.
//        }
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        if (claims != null) {
//            return claimsResolver.apply(claims);
//        }
//        return null; // Or throw a custom exception if required.
//    }
//
//    public String getUserNameFromJwt(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

    public String getUserNameFromJwt(String token)  {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validate(String token){
     try{
         Jwts.parserBuilder()
                 .setSigningKey(SECRET_KEY)
                 .build()
                 .parseClaimsJws(token);
         return true;
     } catch (Exception e){
         throw new AuthenticationCredentialsNotFoundException(e.getMessage());
     }
    }

//    public UsernamePasswordAuthenticationToken
//    getAuthenticationToken(final String token,
//                           Authentication authentication, final UserDetails userDetails) {
//
//        Claims claims =  extractAllClaims(token);
//
//        final Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get("role").toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }

}
