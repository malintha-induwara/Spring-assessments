package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dto.UserDTO;

import java.util.List;

public interface UserService {
    String saveUser(UserDTO userDTO);
    boolean updateUser(String userId,UserDTO userDTO);
    boolean deleteUser(String userId);
    UserDTO getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
