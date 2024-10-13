package lk.ijse.gdse68.notetakerspringboot.controller;


import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.exception.DataPersistFailedException;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.JWTResponse;
import lk.ijse.gdse68.notetakerspringboot.jwtmodels.SignIn;
import lk.ijse.gdse68.notetakerspringboot.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping(value = "signUp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse> signUp(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic){
        try {

            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);

            UserDTO buildUserDTO = new UserDTO();
            buildUserDTO.setFirstName(firstName);
            buildUserDTO.setLastName(lastName);
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(password);
            buildUserDTO.setProfilePic(base64ProfilePic);

            userService.saveUser(buildUserDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    //In here we called this conversion "Serialization"
    //Its the process of a data that comes from network request to a  object
    @PostMapping(value = "signIn")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn){
        return null;
    }
}

