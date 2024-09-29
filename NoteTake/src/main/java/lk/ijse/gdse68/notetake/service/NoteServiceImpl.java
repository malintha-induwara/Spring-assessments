package lk.ijse.gdse68.notetake.service;

import lk.ijse.gdse68.notetake.customObj.NoteErrorResponse;
import lk.ijse.gdse68.notetake.customObj.NoteResponse;
import lk.ijse.gdse68.notetake.dao.NoteDao;
import lk.ijse.gdse68.notetake.dto.impl.NoteDTO;
import lk.ijse.gdse68.notetake.entity.Note;
import lk.ijse.gdse68.notetake.exception.DataPersistFailedException;
import lk.ijse.gdse68.notetake.exception.NoteNotFoundException;
import lk.ijse.gdse68.notetake.util.AppUtil;
import lk.ijse.gdse68.notetake.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNoteId());
        Note save = noteDao.save(mapping.convertToNoteEntity(noteDTO));
        if(save == null){
            throw new DataPersistFailedException("Cannot save note");
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO noteDTO) {

        Optional<Note> tempNoteById = noteDao.findById(noteId);

        if (tempNoteById.isEmpty()) {
            throw new NoteNotFoundException("Note not found");
        }

        tempNoteById.get().setNoteDescription(noteDTO.getNoteDescription());
        tempNoteById.get().setNoteTitle(noteDTO.getNoteTitle());
        tempNoteById.get().setCreateDate(noteDTO.getCreateDate());
        tempNoteById.get().setPriorityLevel(noteDTO.getPriorityLevel());


//        Note update = noteDao.save(mapping.convertToNoteEntity(noteDTO));
//        if (update != null) {
//            return true;
//        }

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

    }

    @Override
    public boolean deleteNote(String noteId) {

        noteDao.findById(noteId).ifPresent(note -> noteDao.delete(note));


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
    public NoteResponse getSelectedNote(String noteId) {
        if(noteDao.existsById(noteId)){
            return mapping.convertToNoteDTO(noteDao.getReferenceById(noteId));
        }else {
            return new NoteErrorResponse(0,"Note not found");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<Note> all = noteDao.findAll();
        return mapping.convertToNoteDTOList(all);
//        return saveNoteTmp;
    }
}