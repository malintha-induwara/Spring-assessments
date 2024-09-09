package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.customObj.UserResponse;
import lk.ijse.gdse68.notetake.dto.impl.UserDTO;
import lk.ijse.gdse68.notetake.util.AppUtil;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();

    void hello(AppUtil.ResponseCode yel);
}
