package lk.ijse.gdse68.notetakerspringboot.service;

import lk.ijse.gdse68.notetakerspringboot.dao.UserDao;
import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.JWTResponse;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.SignIn;
import lk.ijse.gdse68.notetakerspringboot.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserDao userDao;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final AuthenticationManager authenticationManager;


    @Override
    public JWTResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTResponse.builder().token(generatedToken).build() ;
    }

    @Override
    public JWTResponse signUp(UserDTO signUp) {
        return null;
    }

    @Override
    public JWTResponse refreshToken(String accessToken) {
        return null;
    }
}

