package lk.ijse.gdse68.springsecurity.config;

import lk.ijse.gdse68.springsecurity.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsService userDetailsService;

    private final JwtFilter jwtFilter;


    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customer -> customer.disable());
        httpSecurity.authorizeHttpRequests((request) -> {

                    request.requestMatchers("/").permitAll();
                    request.requestMatchers("/css/*").permitAll();

                    request.requestMatchers("/api/v1/auth/sign_in", "/api/v1/auth/sign_up").permitAll();
                    request.anyRequest().authenticated();
                }

        );
//        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        httpSecurity.logout(AbstractHttpConfigurer::disable);


//                httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }


//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(User.withUsername("admin").password("{noop}user").roles("ADMIN").build());
//    }


    //############################################################################################################
    //So basically You dont need to defined this explicitly as Spring Security will automatically use the UserDetailsService bean to authenticate the user.
    //you only need to define the UserDetailsService bean and the password encoder Spring Security will automatically use it to authenticate the user.
    //############################################################################################################
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }


}

