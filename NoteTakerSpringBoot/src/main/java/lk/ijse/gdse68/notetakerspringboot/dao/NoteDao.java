package lk.ijse.gdse68.notetakerspringboot.dao;


import lk.ijse.gdse68.notetakerspringboot.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<Note,String> {

}
