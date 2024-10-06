package lk.ijse.gdse68.notetakerspringboot.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String message) {
        super(message);
    }

  public NoteNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
