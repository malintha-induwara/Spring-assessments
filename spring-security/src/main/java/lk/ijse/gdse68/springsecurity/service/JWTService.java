package lk.ijse.gdse68.springsecurity.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(String username);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
