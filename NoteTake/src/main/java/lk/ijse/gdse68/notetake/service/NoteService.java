package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dto.impl.NoteDTO;

import java.util.List;

public  interface NoteService {
    String saveNote(NoteDTO noteDTO);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
