package lk.ijse.gdse68.notetakerspringboot.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class User implements Serializable {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePic;
    private String password;

    //mappedBy = is used to define how user is defined in the note entity
    @OneToMany(mappedBy = "user")
    private List<Note> notes;
}

