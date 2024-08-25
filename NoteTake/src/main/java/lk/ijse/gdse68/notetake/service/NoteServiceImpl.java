package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.dao.NoteDao;
import lk.ijse.gdse68.notetake.dto.NoteDTO;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lk.ijse.gdse68.notetake.util.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping mapping;

    @Override
    public String saveNote(NoteDTO noteDTO) {
//        noteDTO.setNoteId(AppUtil.createNoteId());
//        noteDao.save(mapping.convertToNoteEntity(noteDTO));
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

//        ListIterator<NoteDTO> updateList = saveNoteTmp.listIterator();

//        while (updateList.hasNext()) {
//            NoteDTO dto = updateList.next();
//            if (dto.getNoteId().equals(noteId)) {
//                dto.setNoteTitle(noteDTO.getNoteTitle());
//                dto.setNoteDescription(noteDTO.getNoteDescription());
//                dto.setPriorityLevel(noteDTO.getPriorityLevel());
//                dto.setCreateDate(noteDTO.getCreateDate());
//                return true;
//            }
//        }
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

//        ListIterator<NoteDTO> deleteList = saveNoteTmp.listIterator();
//
//        while (deleteList.hasNext()) {
//            NoteDTO dto = deleteList.next();
//            if (dto.getNoteId().equals(noteId)) {
//                deleteList.remove();
//                return true;
//            }
//        }

        return false;
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
//        for (NoteDTO noteDTO : saveNoteTmp) {
//            if (noteDTO.getNoteId().equals(noteId)) {
//                return noteDTO;
//            }
//        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
//        return saveNoteTmp;
        return null;
    }
}