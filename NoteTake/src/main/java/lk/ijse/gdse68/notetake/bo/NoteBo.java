package lk.ijse.gdse68.notetake.bo;

import lk.ijse.gdse68.notetake.dto.NoteDTO;

import java.util.List;

public sealed interface NoteBo  permits NoteBoImpl {
    String saveNote(NoteDTO noteDTO);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
