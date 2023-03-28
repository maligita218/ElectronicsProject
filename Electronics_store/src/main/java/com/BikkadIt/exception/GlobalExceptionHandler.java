package com.BikkadIt.exception;

import com.BikkadIt.helper.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        logger.error("Initiating exception request to handle");
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, true, HttpStatus.NOT_FOUND);
        logger.error("Completed Exception handling request");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        logger.error("Completed Exception handling request");
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, Object> resp = new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            resp.put(field, message);
        });
        logger.error("Completed Exception handling request");
        return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
    }

    //handle Bad Api Request
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiResponse> handleBadApiRequest(BadApiRequest e) {
        logger.info("Bad api request handle here...");
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false, HttpStatus.BAD_REQUEST);
        logger.error("Completed Exception handling request...");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
