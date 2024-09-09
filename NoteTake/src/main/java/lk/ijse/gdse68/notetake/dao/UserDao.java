package lk.ijse.gdse68.notetake.dao;

import lk.ijse.gdse68.notetake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    User getUserByUserId(String userId);
}
