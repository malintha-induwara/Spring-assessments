package lk.ijse.gdse68.notetake.controller;


import lk.ijse.gdse68.notetake.dto.UserDTO;
import lk.ijse.gdse68.notetake.service.UserService;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {


    @Autowired
    private final UserService userService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String fileName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic
    ){


        //Handle profile pic
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);


        //Build the user object
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(fileName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setProfilePic(base64ProfilePic);


        //Save the user
        String userId = userService.saveUser(userDTO);
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }





}

