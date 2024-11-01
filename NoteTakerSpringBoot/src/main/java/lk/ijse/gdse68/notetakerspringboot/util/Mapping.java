package lk.ijse.gdse68.notetakerspringboot.util;

import lk.ijse.gdse68.notetakerspringboot.dto.impl.NoteDTO;
import lk.ijse.gdse68.notetakerspringboot.dto.impl.UserDTO;
import lk.ijse.gdse68.notetakerspringboot.entity.Note;
import lk.ijse.gdse68.notetakerspringboot.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public NoteDTO convertToNoteDTO(Note note) {
        return modelMapper.map(note, NoteDTO.class);
    }
    public Note convertToNoteEntity(NoteDTO noteDTO) {
        return modelMapper.map(noteDTO, Note.class);
    }
    public List<NoteDTO> convertToNoteDTOList(List<Note> list) {
        return modelMapper.map(list, new TypeToken<List<NoteDTO>>() {}.getType());
//        return modelMapper.map(list, List.class);
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public User convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    public List<UserDTO> convertToUserDTOList(List<User> list) {
        return modelMapper.map(list, List.class);
    }

}

