package lk.ijse.gdse68.notetakerspringboot.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.notetakerspringboot.service.JWTService;
import lk.ijse.gdse68.notetakerspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Configuration
@RequiredArgsConstructor //Used for dependency injection import
public class JWTConfig extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String initToken = request.getHeader("Authorization");
        String userEmail;
        String jwtToken;


        //Initial validation
        if (StringUtils.isEmpty(initToken)|| !initToken.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //Token received
        jwtToken = initToken.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);

        //Token validation
        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            //Load user details based on the email
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwtToken, userDetails)){
                //Token is valid
                //Set the security context
                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                emptyContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(emptyContext);

            }
        }
    }
}

