package lk.ijse.gdse68.notetake.controller;


import lk.ijse.gdse68.notetake.dto.UserDTO;
import lk.ijse.gdse68.notetake.service.UserService;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        userService.hello(AppUtil.ResponseCode.SUCCESS);

        //Save the user
        String userId = userService.saveUser(userDTO);
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")String userId){
        userService.deleteUser(userId);
        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

