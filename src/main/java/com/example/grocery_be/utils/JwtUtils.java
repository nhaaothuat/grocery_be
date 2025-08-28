package com.example.grocery_be.utils;

import com.example.grocery_be.enities.User;
import com.example.grocery_be.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final UserRepository userRepository;
    private static final String SECRET_KEY = "dfasdfadfdjhflkashdfkjasdhlfkjasdhfadsfasdf";


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("CUSTOMER"));
        return generateToken(extraClaims, userDetails);
    }

    private String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return Jwts.builder().claims(extraClaims)
                .subject(userDetails.getUsername())
                .claims(extraClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    public String extractUsername(String token){
        return extraClaim(token, Claims::getSubject);
    }

    public <T>T extraClaim(String token, Function<Claims,T> resolver){
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extraClaim(token,Claims::getExpiration);
    }

    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null && authentication.isAuthenticated()){
            User user =(User) authentication.getPrincipal();
            return userRepository.findById(user.getId()).orElse(null);
        }
        return null;
    }


}
