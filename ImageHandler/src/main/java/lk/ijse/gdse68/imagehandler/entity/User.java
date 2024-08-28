package lk.ijse.gdse68.imagehandler.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private boolean status = true;
}

