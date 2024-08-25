package lk.ijse.gdse68.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.ijse.gdse68.jwt.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@PropertySource(ignoreResourceNotFound = true,value = "classpath:application.properties")
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 12;

    @Value("${jwt.secret}")
    private String secretKey;

    public String getUsernameFromToken(String token){

//        System.out.println("Subject " +getClaimFromToken(token, Claims::getSubject));
//        System.out.println("Audience " +getClaimFromToken(token, Claims::getAudience));
//        System.out.println("Expiration " +getClaimFromToken(token, Claims::getExpiration));
//        System.out.println("Issued At " +getClaimFromToken(token, Claims::getIssuedAt));
//        System.out.println("Issuer " +getClaimFromToken(token, Claims::getIssuer));
//        System.out.println("Id " +getClaimFromToken(token, Claims::getId));
//        System.out.println("Not Before " +getClaimFromToken(token, Claims::getNotBefore));
//        System.out.println("Subject " +getClaimFromToken(token, Claims::getSubject));
//
//
//        Claims fromToken = getAllClaimsFromToken(token);
//        System.out.println("fromToken = " + fromToken);
//
//        String role = getClaimFromToken(token, claims -> (String) claims.get("role"));
//        System.out.println("role = " + role);

        return getClaimFromToken(token, Claims::getSubject);
    }

    public Claims getUserRoleCodeFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }



    //For retrieving any information form token
    //We will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    //Check if the token has expired
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Generate token for user
    public String generateToken(UserDTO userDTO){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",userDTO.getRole());
        return doGenerateToken(claims,userDTO.getEmail());
    }

    //While creating the token
    //1. Define claims of the token , like Issuer,Expiration
    //2.Sign the JWT using the HS512 algorithm and secret



    private String doGenerateToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

