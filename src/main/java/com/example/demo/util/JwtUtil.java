package com.example.demo.util;


//package com.example.login.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String secret = "pp";

    public JwtUtil() {
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith("Bearer ") ? header.substring(7) : null;
    }

    public String generateToken(String username) {
        return JWT.create().withSubject(username).withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + 3600000L)).sign(Algorithm.HMAC256("pp"));
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("pp")).build().verify(token);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("pp")).build().verify(token);
        return decodedJWT.getSubject();
    }
}

