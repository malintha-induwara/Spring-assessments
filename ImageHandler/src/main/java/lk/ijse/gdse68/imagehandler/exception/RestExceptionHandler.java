package lk.ijse.gdse68.imagehandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {

        /*
         * Following Code Can use to Convert StackTrace Into String
         *
         * StringWriter stringWriter = new StringWriter();
         * PrintWriter printWriter = new PrintWriter(stringWriter);
         * e.printStackTrace(printWriter);
         * String stackTrace = stringWriter.toString();
         */
        ErrorResponse error = new ErrorResponse("USER_NOT_FOUND_ERROR", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponse> handleUserCreationException(UserCreationException ex) {
        ErrorResponse error = new ErrorResponse("USER_CREATION_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserDeletedException.class)
    public ResponseEntity<ErrorResponse> handleUserDeletedException(UserDeletedException ex) {
        ErrorResponse error = new ErrorResponse("USER_DELETED_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<ErrorResponse> handleUserUpdateException(UserUpdateException ex) {
        ErrorResponse error = new ErrorResponse("USER_UPDATE_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(ImageDeleteException.class)
    public ResponseEntity<ErrorResponse> handleImageDeleteException(ImageDeleteException ex) {
        ErrorResponse error = new ErrorResponse("IMAGE_DELETE_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidImageTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidImageTypeException(InvalidImageTypeException ex) {
        ErrorResponse error = new ErrorResponse("INVALID_IMAGE_TYPE_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}

