package lk.ijse.gdse68.imagehandler.service;

import lk.ijse.gdse68.imagehandler.dto.UserDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {

    void saveUser(UserDTO dto);

    void updateUser(UserDTO dto);

    void deleteUser(String email);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();

    Resource getImage(String type, Long userId);

    void deleteImage(String type, Long userId);

    void updateImage(String type, Long userId, MultipartFile file);
}
