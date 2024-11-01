package lk.ijse.gdse68.notetakerspringboot.service;

import lk.ijse.gdse68.notetakerspringboot.customObj.UserErrorResponse;
import lk.ijse.gdse68.notetakerspringboot.customObj.UserResponse;
import lk.ijse.gdse68.notetakerspringboot.dao.UserDao;
import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.entity.User;
import lk.ijse.gdse68.notetakerspringboot.exception.DataPersistFailedException;
import lk.ijse.gdse68.notetakerspringboot.exception.UserNotFoundException;
import lk.ijse.gdse68.notetakerspringboot.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        User save = userDao.save(modelMapper.map(userDTO, User.class));
        if (save == null) {
            throw new DataPersistFailedException("User save failed");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<User> userById = userDao.findById(userDTO.getUserId());

        if (userById.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        userById.get().setFirstName(userDTO.getFirstName());
        userById.get().setLastName(userDTO.getLastName());
        userById.get().setEmail(userDTO.getEmail());
        userById.get().setPassword(userDTO.getPassword());
        userById.get().setProfilePic(userDTO.getProfilePic());
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> selectedUserId = userDao.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        if (userDao.existsById(userId)){
            User userEntityByUserId = userDao.getUserByUserId(userId);
            return modelMapper.map(userEntityByUserId, UserDTO.class);
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> getAllUsers = userDao.findAll();
        return modelMapper.map(getAllUsers, new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public void hello(AppUtil.ResponseCode responseCode) {
        if (responseCode == AppUtil.ResponseCode.SUCCESS) {
            System.out.println("Success");
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email ->
                userDao.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("User Not found"));
    }
}

