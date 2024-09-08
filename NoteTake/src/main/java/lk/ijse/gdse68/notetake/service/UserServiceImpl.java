package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dao.UserDao;
import lk.ijse.gdse68.notetake.dto.UserDTO;
import lk.ijse.gdse68.notetake.entity.User;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        userDao.save(modelMapper.map(userDTO, User.class));
        return "User saved successfully";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
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
        return List.of();
    }

    @Override
    public void hello(AppUtil.ResponseCode responseCode) {


        if (responseCode == AppUtil.ResponseCode.SUCCESS) {
            System.out.println("Success");
        }

    }
}

