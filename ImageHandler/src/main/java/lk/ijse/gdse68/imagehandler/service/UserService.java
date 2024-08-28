package lk.ijse.gdse68.imagehandler.service;

import lk.ijse.gdse68.imagehandler.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void saveUser(UserDTO dto);
    void updateUser(UserDTO dto);
    void deleteUser(String email);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();

}
