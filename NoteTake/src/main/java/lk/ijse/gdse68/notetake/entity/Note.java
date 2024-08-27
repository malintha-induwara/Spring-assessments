package lk.ijse.gdse68.notetake.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "notes")
@Entity
public class Note {
    @Id
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;
}

