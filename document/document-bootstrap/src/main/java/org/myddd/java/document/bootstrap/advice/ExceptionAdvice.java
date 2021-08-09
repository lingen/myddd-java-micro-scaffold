package org.myddd.java.document.bootstrap.advice;

import org.myddd.java.document.bootstrap.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice()
public class ExceptionAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<BaseResponse<Object>> runtimeExceptionHandler(RuntimeException exception){
        exception.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok(BaseResponse.fail(-1,exception.getMessage()));
    }
}
