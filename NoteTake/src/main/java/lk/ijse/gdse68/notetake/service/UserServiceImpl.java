package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dao.UserDao;
import lk.ijse.gdse68.notetake.dto.UserDTO;
import lk.ijse.gdse68.notetake.entity.User;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        User save = userDao.save(modelMapper.map(userDTO, User.class));
        if (save != null && save.getUserId() != null) {
            return "User saved successfully";
        }
        return "User saved failed";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        Optional<User> userById = userDao.findById(userId);

        if (userById.isEmpty()) {
            return false;
        }

        userById.get().setFirstName(userDTO.getFirstName());
        userById.get().setLastName(userDTO.getLastName());
        userById.get().setEmail(userDTO.getEmail());
        userById.get().setPassword(userDTO.getPassword());
        userById.get().setProfilePic(userDTO.getProfilePic());
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        if(userDao.existsById(userId)){
            userDao.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        User userEntityByUserId = userDao.getUserByUserId(userId);
        return modelMapper.map(userEntityByUserId, UserDTO.class);
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
}

