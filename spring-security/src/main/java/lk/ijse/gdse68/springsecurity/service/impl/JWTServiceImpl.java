package lk.ijse.gdse68.springsecurity.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.gdse68.springsecurity.service.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTServiceImpl implements JWTService {

    private final String jwtSecret;

//    public JWTServiceImpl() {
//        // Load the secret key from an environment varriable
//        this.jwtSecret = System.getenv("JWT_SECRET");
//        if (this.jwtSecret == null || this.jwtSecret.isEmpty()) {
//            throw new RuntimeException("JWT_SECRET environment variable is not set");
//        }
//    }




    public JWTServiceImpl() {
        jwtSecret = "Q3XP9KeZTEUp32ogm6+nB30/XAS/N1AKyWn5AqJqeZA=";
//        try {
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secretKey = keyGen.generateKey();
//            jwtSecret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//
//            System.out.println(jwtSecret);
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public String generateToken(String username) {

        //Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
//                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 ))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }




    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (!isTokenExpired(token) && userName.equals(userDetails.getUsername()) );
    }
}

