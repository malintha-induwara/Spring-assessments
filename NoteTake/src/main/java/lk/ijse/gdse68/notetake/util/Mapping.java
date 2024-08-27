package lk.ijse.gdse68.notetake.util;

import lk.ijse.gdse68.notetake.dto.NoteDTO;
import lk.ijse.gdse68.notetake.entity.Note;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public NoteDTO convertToNoteDTO(Object object) {
        return modelMapper.map(object, NoteDTO.class);
    }

    public Note convertToNoteEntity(Object object) {
        return modelMapper.map(object, Note.class);
    }

    public List<NoteDTO> convertToNoteDTOList(List<Note> list) {
        return modelMapper.map(list, List.class);
    }

}

