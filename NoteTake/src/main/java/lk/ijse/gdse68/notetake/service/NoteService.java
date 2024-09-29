package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.customObj.NoteResponse;
import lk.ijse.gdse68.notetake.dto.impl.NoteDTO;

import java.util.List;

public  interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
