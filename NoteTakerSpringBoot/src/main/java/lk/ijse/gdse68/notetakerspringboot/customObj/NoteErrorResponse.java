package lk.ijse.gdse68.notetakerspringboot.customObj;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteErrorResponse  implements NoteResponse{
    private int code;
    private String errorMessage;
}




