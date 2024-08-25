package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dto.NoteDTO;
import lk.ijse.gdse68.notetake.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public final class NoteServiceImpl implements NoteService {
    List<NoteDTO> saveNoteTmp = new ArrayList<>();
    public NoteServiceImpl() {
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
        System.out.println(saveNoteTmp);
    }
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        saveNoteTmp.add(noteDTO);
        return "Saved successfully in Service layer";
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO noteDTO) {


        //We dont use following way because when updating data from a running list it can throw
        //ConcurrentModificationException

//        for (NoteDTO dto : saveNoteTmp) {
//            if (dto.getNoteId().equals(noteId)) {
//                dto.setNoteTitle(noteDTO.getNoteTitle());
//                dto.setNoteDescription(noteDTO.getNoteDescription());
//                dto.setPriorityLevel(noteDTO.getPriorityLevel());
//                dto.setCreateDate(noteDTO.getCreateDate());
//                return true;
//            }
//        }

        ListIterator<NoteDTO> updateList = saveNoteTmp.listIterator();

        while (updateList.hasNext()) {
            NoteDTO dto = updateList.next();
            if (dto.getNoteId().equals(noteId)) {
                dto.setNoteTitle(noteDTO.getNoteTitle());
                dto.setNoteDescription(noteDTO.getNoteDescription());
                dto.setPriorityLevel(noteDTO.getPriorityLevel());
                dto.setCreateDate(noteDTO.getCreateDate());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {

//        for (NoteDTO noteDTO : saveNoteTmp) {
//            if (noteDTO.getNoteId().equals(noteId)) {
//                saveNoteTmp.remove(noteDTO);
//                return true;
//            }
//        }

        ListIterator<NoteDTO> deleteList = saveNoteTmp.listIterator();

        while (deleteList.hasNext()) {
            NoteDTO dto = deleteList.next();
            if (dto.getNoteId().equals(noteId)) {
                deleteList.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        for (NoteDTO noteDTO : saveNoteTmp) {
            if (noteDTO.getNoteId().equals(noteId)) {
                return noteDTO;
            }
        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return saveNoteTmp;
    }
}