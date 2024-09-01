package lk.ijse.gdse68.notetake.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {
    @Id
    private String userId;
    private String findName;
    private String lastName;
    private String email;
    private String profilePic;
    private String password;

    //mappedBy = is used to define how user is defined in the note entity
    @OneToMany(mappedBy = "user")
    private List<Note> notes;
}

