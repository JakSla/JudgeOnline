package pl.ejaksla.onlinejudge.infrastracture.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.ejaksla.onlinejudge.problem.dto.ProblemNotFoundException;

/**
 * Created by Jakub Slawinski on 2019-11-13
 */
@ControllerAdvice
class ExceptionHandlingAdvice {

    @ExceptionHandler(ProblemNotFoundException.class)
    ResponseEntity<ErrorMessage> handleNotFoundFilms(ProblemNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @Getter
    @AllArgsConstructor
    class ErrorMessage {
        private String message;
        private String details;

        public ErrorMessage(String message) {
            this.message = message;
        }
    }
}
