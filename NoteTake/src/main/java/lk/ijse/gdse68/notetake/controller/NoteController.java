package lk.ijse.gdse68.notetake.controller;

import lk.ijse.gdse68.notetake.service.NoteService;
import lk.ijse.gdse68.notetake.dto.NoteDTO;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    @Autowired
    private final NoteService noteService;

    //Todo: CRUD of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO noteDTO){
        //Todo: Handle with BO

        noteDTO.setNoteId(AppUtil.createNoteId());
        noteService.saveNote(noteDTO);
        return ResponseEntity.ok("Note Created");
    }

    @GetMapping(value = "/allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId) {
        return noteService.getSelectedNote(noteId);
    }


    @PutMapping(value = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteDTO noteDTO){
        boolean updateNote = noteService.updateNote(noteId, noteDTO);
        if (updateNote) {
            System.out.println("Note Updated");
        }
    }


    @DeleteMapping(value = "/{noteId}")
    public void deleteNote(@PathVariable ("noteId") String noteId){
        System.out.println(noteId+" Deleted");
    }

}

