package lk.ijse.gdse68.imagehandler;

import lk.ijse.gdse68.imagehandler.dto.UserDTO;
import lk.ijse.gdse68.imagehandler.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageHandlerApplicationTests {

    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
    }

    @Test
    void saveUser() {
         UserDTO userDTO = new UserDTO(8L,"Sam","samopppp@gmail.com",null,null);
        userService.saveUser(userDTO);
    }


}
