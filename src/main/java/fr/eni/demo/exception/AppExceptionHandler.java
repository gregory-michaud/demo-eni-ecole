package fr.eni.demo.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.stream.Collectors;
@AllArgsConstructor
@ControllerAdvice
public class AppExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> capturerException(Exception ex){
        return  ResponseEntity.status(HttpStatus.
                NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException ex,
                                                        Locale locale) {

        String message = ex
                .getFieldErrors()
                .stream()
                .map(f -> f.getDefaultMessage())
                .collect(Collectors.joining(" - "));

        //notvalidexception
        String titreErreur = messageSource.getMessage("notvalidexception", null, locale);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(titreErreur + message);
    }
}
