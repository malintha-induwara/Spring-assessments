package lk.ijse.gdse68.notetake.dto.impl;

import lk.ijse.gdse68.notetake.dto.SuperDTO;
import lombok.*;

import java.io.Serializable;




@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NoteDTO implements SuperDTO {
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;
}

