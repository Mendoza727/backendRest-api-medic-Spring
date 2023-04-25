package med.voll.api.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionServerClass {
    
    private record ValidacionCamps(String campo, String error){
        public ValidacionCamps(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundException> NotFound404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity BadRequest400(MethodArgumentNotValidException e) {
        var errorFields = e.getFieldErrors().stream().map(ValidacionCamps::new).toList();
        return ResponseEntity.badRequest().body(errorFields);
    }
    
}
