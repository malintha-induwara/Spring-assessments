package lk.ijse.gdse68.imagehandler.controller;


import lk.ijse.gdse68.imagehandler.dto.UserDTO;
import lk.ijse.gdse68.imagehandler.service.UserService;
import lk.ijse.gdse68.imagehandler.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@ModelAttribute UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Saved");
    }


    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User Updated");
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        UserDTO userByEmail = userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userByEmail);
    }



    @GetMapping("/image/{type:profile|banner}/{userId}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable String type, @PathVariable Long userId){
        Resource resource = userService.getImage(type, userId);
        MediaType contentType = Objects.requireNonNull(resource.getFilename()).endsWith("jpg") ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/image/{type:profile|banner}/{userId}")
    public ResponseEntity<String> deleteImage(@PathVariable String type, @PathVariable Long userId){
        userService.deleteImage(type, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Image Deleted");
    }


    @PutMapping("/image/{type:profile|banner}/{userId}")
    public ResponseEntity<String> updateImage(@PathVariable String type, @PathVariable Long userId, @RequestParam("file") MultipartFile file){
        userService.updateImage(type, userId, file);
        return ResponseEntity.status(HttpStatus.OK).body("Image Updated");
    }



}

