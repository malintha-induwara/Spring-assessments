package lk.ijse.gdse68.notetakerspringboot.service;

import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.JWTResponse;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.SignIn;

public interface AuthenticationService{
    JWTResponse signIn(SignIn signIn);
    JWTResponse signUp(UserDTO signUp);
    JWTResponse refreshToken(String accessToken);
}
