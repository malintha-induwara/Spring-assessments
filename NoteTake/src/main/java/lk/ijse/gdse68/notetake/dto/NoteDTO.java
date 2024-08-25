package lk.ijse.gdse68.notetake.dto;

import java.io.Serializable;

public class NoteDTO implements Serializable {
    private String noteId;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createDate;


    public NoteDTO() {
    }

    public NoteDTO(String noteId, String noteTitle, String noteDescription, String priorityLevel, String createDate) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.priorityLevel = priorityLevel;
        this.createDate = createDate;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId='" + noteId + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}

