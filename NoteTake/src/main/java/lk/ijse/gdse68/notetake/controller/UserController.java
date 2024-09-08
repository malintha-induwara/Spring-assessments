package lk.ijse.gdse68.notetake.controller;


import lk.ijse.gdse68.notetake.customObj.UserResponse;
import lk.ijse.gdse68.notetake.dto.impl.UserDTO;
import lk.ijse.gdse68.notetake.exception.UserNotFoundException;
import lk.ijse.gdse68.notetake.service.UserService;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        String saveStatus = userService.saveUser(userDTO);
        if (saveStatus.contains("User saved successfully")){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")String userId){
        userService.deleteUser(userId);
        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getSelectedUser(@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }



    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id,
                                             @RequestPart("firstName") String updateFirstName,
                                             @RequestPart("lastName") String updateLastName,
                                             @RequestPart("email") String updateEmail,
                                             @RequestPart("password") String updatePassword,
                                             @RequestPart("profilePic") String updateProfilePic){

        try {
            String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
            var updateUser = new UserDTO();
            updateUser.setUserId(id);
            updateUser.setFirstName(updateFirstName);
            updateUser.setLastName(updateLastName);
            updateUser.setPassword(updatePassword);
            updateUser.setEmail(updateEmail);
            updateUser.setProfilePic(updateBase64ProfilePic);
            userService.updateUser(updateUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

