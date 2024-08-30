package lk.ijse.gdse68.springsecurity.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    private Long customerId;
    private String userName;
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean status;
}

