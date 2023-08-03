package med.vol.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import med.vol.api.domain.exception.ExceptionValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(validationDataError::new).toList());
    }

    @ExceptionHandler(ExceptionValidation.class)
    public ResponseEntity businessRuleError(ExceptionValidation exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    private record validationDataError(String field, String message) {

        public validationDataError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());

        }
    }

}
