package lk.ijse.gdse68.notetake.dto;

import lombok.*;

import java.io.Serializable;




@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NoteDTO implements Serializable {
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;
}

