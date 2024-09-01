package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dto.UserDTO;

import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public String saveUser(UserDTO userDTO) {
        return "";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }
}

