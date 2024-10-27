package lk.ijse.gdse68.notetakerspringboot.dao;

import lk.ijse.gdse68.notetakerspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    User getUserByUserId(String userId);
    Optional<User> findByEmail(String email);
}
