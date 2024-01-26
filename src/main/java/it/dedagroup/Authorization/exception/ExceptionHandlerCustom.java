package it.dedagroup.Authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.Authorization.exception.response.ErrorMessageDTOResponse;
import it.dedagroup.Authorization.exception.response.ErrorMessageListDTOResponse;
import it.dedagroup.Authorization.exception.response.ResponseStatusExceptionCustomMessageDTOResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerCustom {

	//Handler per controllare i Requestbody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorMessageListDTOResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Errore di validazione per l'argomento del metodo: {}", e.getMessage());

        ErrorMessageListDTOResponse errors = new ErrorMessageListDTOResponse();
        for (FieldError fieldError : e.getFieldErrors()) {
            ErrorMessageDTOResponse error = new ErrorMessageDTOResponse();
            error.setMessage(fieldError.getDefaultMessage());
            error.setFieldName(fieldError.getField());

            if (!"password".equals(fieldError.getField())) {
                Object rejectedValue = fieldError.getRejectedValue();
                
                if (rejectedValue != null) {
                    error.setInvalid_Data(rejectedValue.toString());
                } else {
                    error.setInvalid_Data("N/A");
                }        
            } else {
            	error.setInvalid_Data("N/A");
            }

            errors.getViolations().add(error);
        }
        return errors;
    }

    //Handler per controllare le ConstraintViolations(PathVariable e RequestParam)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorMessageListDTOResponse onConstraintValidationException(ConstraintViolationException e){
        log.error("Violazione di Constraint: {}", e.getMessage());

        ErrorMessageListDTOResponse error=new ErrorMessageListDTOResponse();
        for (ConstraintViolation violation:e.getConstraintViolations()) {
            ErrorMessageDTOResponse messaggioErrore= new ErrorMessageDTOResponse();
            messaggioErrore.setFieldName(violation.getPropertyPath().toString());
            messaggioErrore.setMessage(violation.getMessage());
            messaggioErrore.setInvalid_Data(violation.getInvalidValue().toString());
            error.getViolations().add(messaggioErrore);
        }
        return error;
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    ResponseStatusExceptionCustomMessageDTOResponse onResponseStatusException(ResponseStatusException e){
        log.warn("ResponseStatusException lanciata: Status {} Reason {}", e.getStatusCode(), e.getReason());

    	ResponseStatusExceptionCustomMessageDTOResponse error = new ResponseStatusExceptionCustomMessageDTOResponse();
        error.setCode(e.getStatusCode());
        error.setReason(e.getReason());
        error.setStatusCodeNumber(e.getStatusCode().value());
        return error;
    }
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessageDTOResponse> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Eccezione non gestita catturata: ", ex);

        ErrorMessageDTOResponse error = new ErrorMessageDTOResponse();
        error.setMessage("Si è verificato un errore interno del server.");
        error.setFieldName("Errore Interno");
        error.setInvalid_Data("N/A");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
