package lk.ijse.gdse68.notetakerspringboot.service;

import lk.ijse.gdse68.notetakerspringboot.customObj.UserResponse;
import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.util.AppUtil;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
    void hello(AppUtil.ResponseCode yel);
    UserDetailsService userDetailsService();
}
