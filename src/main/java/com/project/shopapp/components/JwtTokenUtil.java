package com.project.shopapp.components;

import com.project.shopapp.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration; // hạn thoi gian su dung token

    @Value("${jwt.secretkey}")
    private String secretkey;

    public String generateToken(User user) throws Exception{
        Map<String, Object> claims = new HashMap<>();
        //this.generateSecretkey();
        claims.put("phoneNumber", user.getPhoneNumber());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getPhoneNumber())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration*1000L))
                    .signWith(getSignKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }catch (Exception e)
        {
            throw new InvalidParameterException("can not reate jwt"+e.getMessage());

        }
    }


    private Key getSignKey() {
        byte[] bytes = Decoders.BASE64.decode(secretkey);
        // Decoders.BASE64.decode("zlUHsd3qmbNH/KPryopIfKwXDpSMVNsfGLJDawWiYYA=")
        //Keys.hmacShaKeyFor(Decoders.BASE64.decode("zlUHsd3qmbNH/KPryopIfKwXDpSMVNsfGLJDawWiYYA="))
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateSecretkey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String secretkey = Encoders.BASE64.encode(bytes);
        return secretkey;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractPhoneNumber(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
