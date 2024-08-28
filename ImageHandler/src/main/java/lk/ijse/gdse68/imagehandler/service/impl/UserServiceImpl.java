package lk.ijse.gdse68.imagehandler.service.impl;

import lk.ijse.gdse68.imagehandler.dto.UserDTO;
import lk.ijse.gdse68.imagehandler.entity.User;
import lk.ijse.gdse68.imagehandler.exception.UserCreationException;
import lk.ijse.gdse68.imagehandler.exception.UserNotFoundException;
import lk.ijse.gdse68.imagehandler.exception.UserUpdateException;
import lk.ijse.gdse68.imagehandler.mapper.UserMapper;
import lk.ijse.gdse68.imagehandler.repository.UserRepository;
import lk.ijse.gdse68.imagehandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(UserDTO dto) {
        //Convert the DTO to an entity
        User user = userMapper.userDtoToUser(dto);
        //Check if the user already exists
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserCreationException("User already exists with the email: " + user.getEmail());
        });
        try {
            //If the user does not exist, save the user
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserCreationException("An unexpected error occurred while creating the user.", e);
        }
    }

    @Override
    public void updateUser(UserDTO dto) {
        //Convert the DTO to an entity
        User user = userMapper.userDtoToUser(dto);
        //Check if the user exists
        userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("User does not exist with the id: " + user.getId()));
        try {
            //If the user exists, update the user
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserUpdateException("An unexpected error occurred while updating the user.", e);
        }
    }

    @Override
    public void deleteUser(String email) {
        //Check if the user exists
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User does not exist with the email: " + email));
        try {
            //If the user exists, delete the user
            userRepository.delete(user);
        } catch (Exception e) {
            throw new UserCreationException("An unexpected error occurred while deleting the user.", e);
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User does not exist with the email: " + email));
        //Convert the entity to a DTO
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        //Get all the users
        List<User> users = userRepository.findAll();
        //Convert the entities to DTOs
        return userMapper.usersToUserDto(users);
    }
}

