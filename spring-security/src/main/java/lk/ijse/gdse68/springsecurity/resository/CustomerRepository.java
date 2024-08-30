package lk.ijse.gdse68.springsecurity.resository;

import lk.ijse.gdse68.springsecurity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.userName=:username")
    Optional<Customer> findByCustomerUsernameAndPassword(@Param("username") String username);

//    @Query("SELECT c FROM Customer c WHERE c.userName=:username")
    Optional<Customer> findByUserName(String username);

//    Optional<Customer> findByUserNameAndPassword(String username, String password);

}
