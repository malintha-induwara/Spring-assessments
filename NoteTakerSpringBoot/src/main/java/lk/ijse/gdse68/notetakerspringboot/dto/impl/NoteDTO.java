package lk.ijse.gdse68.notetakerspringboot.dto.impl;

import lk.ijse.gdse68.notetakerspringboot.customObj.NoteResponse;
import lk.ijse.gdse68.notetakerspringboot.dto.SuperDTO;
import lombok.*;

import java.io.Serializable;




@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NoteDTO implements SuperDTO, NoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;
    private String userId;
}

