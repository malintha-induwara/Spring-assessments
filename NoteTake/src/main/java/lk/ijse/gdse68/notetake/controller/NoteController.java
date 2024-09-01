package lk.ijse.gdse68.notetake.controller;

import lk.ijse.gdse68.notetake.service.NoteService;
import lk.ijse.gdse68.notetake.dto.NoteDTO;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("health")
    public String healthChecker(){
        return "Note Taker is running";
    }

    //Todo: CRUD of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO noteDTO){
        //Todo: Handle with BO
        String saveNote = noteService.saveNote(noteDTO);
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
    public ResponseEntity<String> updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteDTO noteDTO){
        return noteService.updateNote(noteId, noteDTO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable ("noteId") String noteId){
        return noteService.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

