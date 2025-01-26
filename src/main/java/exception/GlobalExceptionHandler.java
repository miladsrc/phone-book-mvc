package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContactAPIException.class)
    public ResponseEntity<ErrorDetails> handelContactApiException(ContactAPIException contactAPIException,
                                                                  WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                contactAPIException.getMessage(),
                webRequest.getDescription((false)
                ));
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
