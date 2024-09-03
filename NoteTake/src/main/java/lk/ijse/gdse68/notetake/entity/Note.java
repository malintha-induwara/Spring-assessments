package lk.ijse.gdse68.notetake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "notes")
@Entity
public class Note  implements SuperEntity{
    @Id
    private String noteId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;
}

