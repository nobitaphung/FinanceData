package com.pmkha.stockdata.utils;


import com.pmkha.stockdata.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.json.simple.JSONValue;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    private String jwtSecret = "AmbitionLoveFlora";

    private int jwtExpirationMs = 8640000;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Map obj = new HashMap();
        obj.put("user_id", userPrincipal.getId());
        obj.put("user_name", userPrincipal.getUsername());
        obj.put("user_email", userPrincipal.getEmail());
        obj.put("role", userPrincipal.getRole());
        obj.put("iat", (new Date().getTime()));
        obj.put("exp", (new Date((new Date()).getTime() + jwtExpirationMs)).getTime());

        String payload = JSONValue.toJSONString(obj);

        return Jwts.builder()
                .setPayload(payload)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        System.out.println(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("user_name"));
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("user_name").toString();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        }

        return false;
    }
}
