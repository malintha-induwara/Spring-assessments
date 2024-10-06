package lk.ijse.gdse68.notetakerspringboot.service;

import lk.ijse.gdse68.notetakerspringboot.customObj.NoteResponse;
import lk.ijse.gdse68.notetakerspringboot.dto.impl.NoteDTO;

import java.util.List;

public  interface NoteService {
    void saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteResponse getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
