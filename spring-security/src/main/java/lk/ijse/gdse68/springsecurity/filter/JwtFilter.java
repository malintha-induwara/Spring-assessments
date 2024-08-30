package lk.ijse.gdse68.springsecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.springsecurity.service.JWTService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(JwtFilter.class);
    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JWTService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            userName = jwtService.extractUsername(token);
//        }

        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())){
                    token = cookie.getValue();

                    try {
                        userName = jwtService.extractUsername(token);
                    }catch (Exception e){
                        //log.error("e: ", e);
                       e.printStackTrace();
                    }
                    break;
                }
            }
        }




//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            Optional<String> jwtToken = Arrays.stream(cookies)
//                    .filter(cookie -> "jwt".equals(cookie.getName()))
//                    .map(Cookie::getValue)
//                    .findFirst();
//
//            jwtToken.ifPresent(token -> userName = jwtService.extractUsername(token));
//        }



        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);
    }
}

