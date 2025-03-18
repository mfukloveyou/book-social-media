package org.example.authservice.exception;

import org.example.authservice.dto.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ApiResponse> handleServiceException(ServiceException e){
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String enumKey = e.getBindingResult().getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf("INVALID_KEY");
        try{
           errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException ex){

        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);


    }

}
